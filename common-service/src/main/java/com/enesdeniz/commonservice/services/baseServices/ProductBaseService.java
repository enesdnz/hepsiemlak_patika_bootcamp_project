package com.enesdeniz.commonservice.services.baseServices;

import com.enesdeniz.commonservice.entities.Product;
import com.enesdeniz.commonservice.dto.response.ProductResponse;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

public class ProductBaseService {

    /**
     * this method converts product object into product response model
     * @param product
     * @return
     */
    protected ProductResponse convertToProductResponse(Product product) {
        ProductResponse response = new ProductResponse();
        response.setUser(product.getUser());
        response.setPurchaseDate(product.getPurchaseDate());
        response.setEndDate(product.getEndDate());
        response.setRemainingPiece(product.getRemainingPiece());
        return response;
    }

    /**
     * this method returns current date
     * @return
     */
    protected LocalDateTime getCurrentTime() {

        return LocalDateTime.now();
    }

    /**
     * this method adds 30 days to the given date
     * @param previousEndDate
     * @return
     */
    protected Date addThirtyDays(LocalDateTime previousEndDate){

        LocalDateTime date = LocalDateTime.parse(previousEndDate.toString());
        // add 30 days
        date.plusDays(30);
        return Date.from(date.toInstant(ZoneOffset.UTC));
    }
}
