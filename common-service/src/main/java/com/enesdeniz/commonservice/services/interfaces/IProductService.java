package com.enesdeniz.commonservice.services.interfaces;

import com.enesdeniz.commonservice.dto.response.ProductResponse;
import com.enesdeniz.commonservice.entities.Product;
import com.enesdeniz.commonservice.entities.User;
import com.enesdeniz.commonservice.core.Result;
import com.enesdeniz.commonservice.dto.request.PaymentRequest;

public interface IProductService {

    Result buyProduct(PaymentRequest paymentRequest);

    ProductResponse getProductByUserId(Long userId);

    void updateProduct(Product product);

    void buyNewProduct(PaymentRequest request);

    void createProduct(User user);

    void updateRemainingPiece(Long userId);
}
