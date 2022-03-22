package com.enesdeniz.paymentservice.controller;

import com.enesdeniz.paymentservice.core.Result;
import com.enesdeniz.paymentservice.dto.request.PaymentRequest;
import com.enesdeniz.paymentservice.dto.response.PaymentResponse;
import com.enesdeniz.paymentservice.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping
    public ResponseEntity<?> savePayment(@RequestBody PaymentRequest request) {

        Result result = paymentService.savePayment(request);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
