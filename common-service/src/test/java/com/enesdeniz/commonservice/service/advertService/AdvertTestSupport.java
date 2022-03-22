package com.enesdeniz.commonservice.service.advertService;

import com.enesdeniz.commonservice.dto.request.AdvertRequest;
import com.enesdeniz.commonservice.entities.Advert;
import com.enesdeniz.commonservice.entities.User;
import com.enesdeniz.commonservice.enums.UserType;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class AdvertTestSupport {

    protected List<Advert> prepareAdvertList() {
        List<Advert> adverts = new ArrayList<Advert>();
        adverts.add(prepareAdvert("title1"));
        adverts.add(prepareAdvert("title2"));
        adverts.add(prepareAdvert("başlık3"));
        return adverts;
    }

    protected Advert prepareAdvert(String title) {
        Advert advert = new Advert();
        advert.setAdvertNo(0);
        advert.setTitle(title);
        advert.setPrice(new BigDecimal(1500));
        return advert;
    }

    protected User prepareUser() {
        User user = new User(UserType.INDIVIDUAL, "Enes Deniz", "enodeniz190@gmail.com", "12345");
        return user;
    }

    protected AdvertRequest prepareAdvertRequest() {
        AdvertRequest request = new AdvertRequest();
        request.setCreatorUserId(123L);
        request.setTitle("baslik");
        request.setPeriod(30);
        request.setPrice(new BigDecimal(1500));
        return request;
    }
}
