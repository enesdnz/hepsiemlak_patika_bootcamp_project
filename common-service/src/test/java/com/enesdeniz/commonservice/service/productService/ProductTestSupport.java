package com.enesdeniz.commonservice.service.productService;

import com.enesdeniz.commonservice.dto.request.UserRequest;
import com.enesdeniz.commonservice.entities.Product;
import com.enesdeniz.commonservice.entities.User;
import com.enesdeniz.commonservice.enums.UserType;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ProductTestSupport {

    protected Product prepareProduct(){

        Product product = new Product();

        product.setUser(prepareUser());
        product.setRemainingPiece(10);
        product.setPurchaseDate(parseDate("21.03.2022"));
        product.setEndDate(parseDate("21.04.2022"));

        return product;
    }

    protected User prepareUser(){
        User user = new User(UserType.INDIVIDUAL, "Enes", "enesdeniz@gmail.com","12345");
        return user;
    }

    protected static Date parseDate(String date) {
        try {
            return new SimpleDateFormat("dd-MM-yyyy").parse(date);
        } catch (ParseException e) {
            return null;
        }
    }
}
