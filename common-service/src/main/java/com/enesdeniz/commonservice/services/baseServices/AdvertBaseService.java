package com.enesdeniz.commonservice.services.baseServices;

import com.enesdeniz.commonservice.entities.Advert;
import com.enesdeniz.commonservice.entities.User;
import com.enesdeniz.commonservice.enums.AdvertStatus;
import com.enesdeniz.commonservice.dto.request.AdvertRequest;
import com.enesdeniz.commonservice.dto.response.AdvertResponse;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.Optional;
import java.util.Random;

@Slf4j
public class AdvertBaseService {

    protected AdvertResponse convertToAdvertResponse(Advert advert) {
        AdvertResponse response = new AdvertResponse();
        response.setAdvertNo(advert.getAdvertNo());
        response.setCreateDate(advert.getCreateDate());
        response.setUpdateDate(advert.getUpdateDate());
        response.setTitle(advert.getTitle());
        response.setPrice(advert.getPrice());
        response.setPeriod(advert.getPeriod());
        response.setCreatorUser(advert.getCreatorUser());
        response.setAdvertStatus(advert.getAdvertStatus());
        return response;
    }

    protected Advert convertToAdvert(AdvertRequest request, Optional<User> foundUser) {

        Advert advert = null;

        if (foundUser.isPresent()) {
            advert = new Advert();

            //we create random advert number
            Integer advertNo = createAdvertNo();
            advert.setAdvertNo(advertNo);
            advert.setCreateDate(Date.from(getCurrentTime().toInstant(ZoneOffset.UTC)));
            advert.setUpdateDate(Date.from(getCurrentTime().toInstant(ZoneOffset.UTC)));
            advert.setCreatorUser(foundUser.get());
            advert.setTitle(request.getTitle());
            advert.setPeriod(request.getPeriod());
            advert.setPrice(request.getPrice());
            advert.setAdvertStatus(AdvertStatus.IN_REVIEW);
        } else {
            log.info("Advert not found!");
        }

        return advert;
    }

    protected Integer createAdvertNo(){

        Random random = new Random();
        int randomAdvertNo = random.nextInt(300000);
        return randomAdvertNo;

    }

    protected LocalDateTime getCurrentTime(){

        return LocalDateTime.now();
    }
}
