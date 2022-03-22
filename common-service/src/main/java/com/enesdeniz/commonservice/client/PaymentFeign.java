package com.enesdeniz.commonservice.client;

import com.enesdeniz.commonservice.dto.request.PaymentRequest;
import com.enesdeniz.commonservice.dto.response.PaymentResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "payment-service", url = "http://localhost:5860/payments")
public interface PaymentFeign {

    @PostMapping
    ResponseEntity<PaymentResponse> savePayment(@RequestBody PaymentRequest request);

}
