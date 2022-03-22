package com.enesdeniz.commonservice.services;

import com.enesdeniz.commonservice.dto.response.ProductResponse;
import com.enesdeniz.commonservice.entities.Product;
import com.enesdeniz.commonservice.entities.User;
import com.enesdeniz.commonservice.repository.ProductRepository;
import com.enesdeniz.commonservice.repository.UserRepository;
import com.enesdeniz.commonservice.services.baseServices.ProductBaseService;
import com.enesdeniz.commonservice.client.PaymentFeignClient;
import com.enesdeniz.commonservice.core.ErrorResult;
import com.enesdeniz.commonservice.core.Result;
import com.enesdeniz.commonservice.core.SuccessResult;
import com.enesdeniz.commonservice.dto.request.PaymentRequest;
import com.enesdeniz.commonservice.services.interfaces.IProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.Optional;

@Service
@Slf4j
public class ProductService extends ProductBaseService implements IProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PaymentFeignClient paymentFeign;


    @Override
    public Result buyProduct(PaymentRequest paymentRequest){

        Optional<User> user = userRepository.findById(paymentRequest.getUserId());

        if(user.isPresent())
        {
            Optional<Product> product = productRepository.getProductByUserId(user.get().getId());

            if(product.isPresent())
            {
                // if there is a product before, payment is taken and the product is updated
                paymentFeign.savePayment(paymentRequest);
                updateProduct(product.get());
                return new SuccessResult("Your product purchase has been update successfully.");
            }
            else{
                // If there is no product, its payment is taken and a new product is created.
                paymentFeign.savePayment(paymentRequest);
                buyNewProduct(paymentRequest);
                return new SuccessResult("Your product purchase has been made successfully.");
            }
        }
        else{
            log.error("User does not exist");
            return new ErrorResult(false, "User does not exist");
        }

    }

    @Override
    public ProductResponse getProductByUserId(Long userId){

        Optional<Product> product = productRepository.getProductByUserId(userId);

        if(product.isPresent())
        {
            log.info("Get product from Db -> userId : " + userId);
            return convertToProductResponse(product.get());
        }
        else
            return new ProductResponse();
    }


    public void updateProduct(Product product) {

        try {
            Optional<Product> productOptional = productRepository.findById(product.getId());
            if (!productOptional.isPresent()) {
                log.info("No items purchased to update were found");
            }
            Product updateProduct = productOptional.get();
            updateProduct.setRemainingPiece(product.getRemainingPiece() + 10);
            updateProduct.setEndDate(addThirtyDays(LocalDateTime.ofInstant(product.getEndDate().toInstant(),
                ZoneId.systemDefault())));

            productRepository.save(updateProduct);

            log.info("Product Successfully Updated with this id:{}", product.getId());

        } catch (Exception e) {
            log.error("Error :{} while updating Product of event message:{}", e, e.getMessage());
        }
    }

    @Override
    public void buyNewProduct(PaymentRequest request){

        Optional<User> user = userRepository.findById(request.getUserId());

        if(user.isPresent()){

            // If there is a user registration, it creates a new product.
            createProduct(user.get());
        }

    }

    public void createProduct(User user){

        Product product = new Product();

        product.setUser(user);
        product.setRemainingPiece(10);
        product.setPurchaseDate(Date.from(getCurrentTime().toInstant(ZoneOffset.UTC)));
        product.setEndDate(addThirtyDays(getCurrentTime()));

        productRepository.save(product);

        log.info("Created new product -> product: " + product + " -> userId: " + user.getId());

    }

    @Override
    public void updateRemainingPiece(Long userId){

        Optional<Product> productOptional = productRepository.findProductByUserId(userId);
        if (!productOptional.isPresent()) {
            log.info("No purchased items for the user found -> userId: " + userId );
        }
        Product updateProduct = productOptional.get();
        updateProduct.setRemainingPiece(updateProduct.getRemainingPiece() - 1);

        productRepository.save(updateProduct);

        log.info("Update product -> userId: " + userId + "and product: " + updateProduct);
    }

}
