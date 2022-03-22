package com.enesdeniz.commonservice.client;

import com.enesdeniz.commonservice.dto.request.PaymentRequest;
import com.enesdeniz.commonservice.dto.response.PaymentResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * this class helps to send request to payment service with feign client
 */
@Service
@Slf4j
public class PaymentFeignClient {

    @Autowired
    private PaymentFeign paymentFeign;

    public ResponseEntity<PaymentResponse> savePayment(PaymentRequest request) {
        return paymentFeign.savePayment(request);
    }



}
