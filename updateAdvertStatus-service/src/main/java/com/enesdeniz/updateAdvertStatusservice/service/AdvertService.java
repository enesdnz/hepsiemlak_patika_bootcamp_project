package com.enesdeniz.updateAdvertStatusservice.service;

import com.enesdeniz.updateAdvertStatusservice.entities.Advert;
import com.enesdeniz.updateAdvertStatusservice.enums.AdvertStatus;
import com.enesdeniz.updateAdvertStatusservice.repository.AdvertRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AdvertService {

    @Autowired
    private AdvertRepository advertRepository;

    /**
     * updates the status of the newly created advert object
     * @param advert
     */
    public Advert updateAdvertStatus(Advert advert){

        advert.setAdvertStatus(AdvertStatus.ACTIVE);
        advertRepository.save(advert);

        log.info("The status no: " + advert.getAdvertNo() + " advert has been updated.");

        return advert;


    }
}
