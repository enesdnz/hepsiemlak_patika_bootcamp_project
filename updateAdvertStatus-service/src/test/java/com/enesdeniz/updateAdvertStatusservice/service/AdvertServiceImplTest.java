package com.enesdeniz.updateAdvertStatusservice.service;

import com.enesdeniz.updateAdvertStatusservice.entities.Advert;
import com.enesdeniz.updateAdvertStatusservice.entities.User;
import com.enesdeniz.updateAdvertStatusservice.enums.AdvertStatus;
import com.enesdeniz.updateAdvertStatusservice.enums.UserType;
import com.enesdeniz.updateAdvertStatusservice.repository.AdvertRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * this class tests the advert service for the updateAdvertStatus microservice
 */

@SpringBootTest
public class AdvertServiceImplTest {

    @Mock
    private AdvertRepository advertRepository;

    @InjectMocks
    private AdvertService advertService;

    @Test
    @DisplayName("Advert status should be active as a result of the test")
    void updateAdvertStatusTest(){

        Advert advert = prepareAdvert();

        Advert updatedAdvert = advertService.updateAdvertStatus(advert);

        assertEquals(updatedAdvert.getAdvertStatus(), AdvertStatus.ACTIVE);

    }

    // We are creating a advert to use in the test
    Advert prepareAdvert(){
        Advert advert = new Advert();

        advert.setAdvertNo(12345);
        advert.setAdvertStatus(AdvertStatus.IN_REVIEW);
        advert.setCreateDate(parseDate("21.03.2022"));
        advert.setExamine(true);
        advert.setCreatorUser(prepareUser());
        advert.setForward(true);
        advert.setTitle("baslik");
        advert.setPeriod(30);
        advert.setPrice(new BigDecimal(1500));

        return advert;
    }

    // We are creating a user to use in the test
    User prepareUser(){
        User user = new User(1L, UserType.INDIVIDUAL, "Enes", "enesdeniz@gmail.com","12345");
        return user;
    }

    // converting string data to date
    private static Date parseDate(String date) {
        try {
            return new SimpleDateFormat("dd-MM-yyyy").parse(date);
        } catch (ParseException e) {
            return null;
        }
    }
}
