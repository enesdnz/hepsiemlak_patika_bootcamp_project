package com.enesdeniz.paymentservice.service;

import com.enesdeniz.paymentservice.core.Result;
import com.enesdeniz.paymentservice.core.SuccessResult;
import com.enesdeniz.paymentservice.dto.request.PaymentRequest;
import com.enesdeniz.paymentservice.entities.Payment;
import com.enesdeniz.paymentservice.repository.PaymentRepository;
import com.enesdeniz.paymentservice.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@Slf4j
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private UserRepository userRepository;

    public Result savePayment(PaymentRequest request){

        Payment payment = convertToPaymentRequest(request);

        paymentRepository.save(payment);
        log.info(payment  + " adding to DB");

        return new SuccessResult("Your payment completed successfully");

    }

    public Payment convertToPaymentRequest(PaymentRequest paymentRequest){

        Payment payment = new Payment();
        payment.setUser(userRepository.getById(paymentRequest.getUserId()));
        payment.setPaymentDate(getCurrentTime());
        payment.setSuccess(true);

        return payment;
    }

    private LocalDate getCurrentTime() {

        LocalDate lt = LocalDate.now();
        return lt;
    }

}
