package com.enesdeniz.commonservice.services;

import com.enesdeniz.commonservice.core.ErrorResult;
import com.enesdeniz.commonservice.core.SuccessResult;
import com.enesdeniz.commonservice.entities.Advert;
import com.enesdeniz.commonservice.entities.Product;
import com.enesdeniz.commonservice.entities.User;
import com.enesdeniz.commonservice.enums.AdvertStatus;
import com.enesdeniz.commonservice.exception.EntityIsNullException;
import com.enesdeniz.commonservice.repository.AdvertRepository;
import com.enesdeniz.commonservice.repository.ProductRepository;
import com.enesdeniz.commonservice.repository.UserRepository;
import com.enesdeniz.commonservice.services.baseServices.AdvertBaseService;
import com.enesdeniz.commonservice.services.interfaces.IAdvertService;
import com.enesdeniz.commonservice.core.Result;
import com.enesdeniz.commonservice.dto.request.AdvertRequest;
import com.enesdeniz.commonservice.dto.response.AdvertResponse;
import com.enesdeniz.commonservice.queue.QueueService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.ZoneOffset;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AdvertService extends AdvertBaseService implements IAdvertService {

    @Autowired
    private AdvertRepository advertRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private QueueService queueService;

    @Autowired
    private ProductService productService;


    @Override
    public List<AdvertResponse> getAllAdverts(){
        log.info("get all adverts from DB");
        List<Advert> adverts = advertRepository.findAll();
        return adverts.stream().map(this::convertToAdvertResponse).collect(Collectors.toList());

    }

    @Override
    public AdvertResponse getAdvertByAdvertNo(int advertNo){
        log.info("Get one advert from Db -> no : " + advertNo);
        Optional<Advert> advert = advertRepository.findByAdvertNo(advertNo);

        if(advert.isPresent())
            return convertToAdvertResponse(advert.get());
        else
            return new AdvertResponse();
    }

    @Override
    public List<AdvertResponse> getAdvertByAdvertStatus(AdvertStatus advertStatus){

        log.info("Get one advert from Db -> Status : " + advertStatus.toString());

        List<Advert> adverts = advertRepository.getAdvertByAdvertStatus(advertStatus);

        return adverts.stream().map(this::convertToAdvertResponse).collect(Collectors.toList());

    }
    @Override
    public Result saveAdvert(AdvertRequest request) throws EntityIsNullException {

        Optional<User> foundUser = userRepository.findById(request.getCreatorUserId());

        Optional<Product> hasProduct = productRepository.getProductByUserId(foundUser.get().getId());

        if(hasProduct.isPresent() && hasProduct.get().getRemainingPiece() >= 1
            && hasProduct.get().getEndDate().after(Date.from(getCurrentTime().toInstant(ZoneOffset.UTC))))
        {
            Advert advert = convertToAdvert(request, foundUser);

            if (advert == null) {
                log.error("An error occurred while saving the advert");
                throw new EntityIsNullException("advert object is null");
            }

            Advert savedAdvert = advertRepository.save(advert);
            log.info(savedAdvert  + " adding to DB");

            // a right is deducted from the purchased product because a new advert is added
            productService.updateRemainingPiece(foundUser.get().getId());
            log.info(foundUser.get().getId()  + " user's product usage rights have decreased by one");

            // Activates the status of the newly added advert.
            queueService.updateAdvertStatus(advert);
            log.info(advert  + " status activated");

            return new SuccessResult("New advert added successfully " + savedAdvert);
        }

        return new ErrorResult(false, "You are not entitled to the purchased product");

    }

    @Override
    public Result updateAdvert(Long advertNo, AdvertRequest request) {
        try {
            if(request.getAdvertStatus() == AdvertStatus.IN_REVIEW){
                log.error("Advert number" + advertNo +"cannot be withdrawn to review status.");
                return new ErrorResult(false, "Users cannot change adverts with in review status");
            }


            Advert advert = advertRepository.findById(advertNo).orElseThrow(EntityNotFoundException::new);
            advert.setTitle(request.getTitle());
            advert.setPrice(request.getPrice());
            advert.setPeriod(request.getPeriod());
            advert.setAdvertStatus(request.getAdvertStatus());
            advert.setUpdateDate(Date.from(getCurrentTime().toInstant(ZoneOffset.UTC)));
            advertRepository.save(advert);

            log.info(advert  + " updating to DB");

            return new SuccessResult("Advert id : " + advertNo + " updated successfully");
        } catch (EntityNotFoundException e) {
            log.error("No such advert found");
            return new ErrorResult(false, "No such advert found");
        }
    }

    @Override
    public Result deleteAdvert(Long advertNo){

        Optional<Advert> advert = advertRepository.findById(advertNo);
        if(advert.isPresent())
        {
            advertRepository.deleteById(advertNo);
            log.info("Deleted advert ->  no: " + advertNo );
            return new SuccessResult("Advert id : " + advertNo + " removed successfully");
        }
        else{
            log.error("No such advert found");
            return new ErrorResult(false, "No such advert found");
        }

    }

}
