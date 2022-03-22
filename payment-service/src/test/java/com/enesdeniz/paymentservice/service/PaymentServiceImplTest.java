package com.enesdeniz.paymentservice.service;

import com.enesdeniz.paymentservice.entities.Payment;
import com.enesdeniz.paymentservice.entities.User;
import com.enesdeniz.paymentservice.enums.UserType;
import com.enesdeniz.paymentservice.repository.PaymentRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;

/**
 * this class tests the payment service for the payment microservice
 */

@SpringBootTest
public class PaymentServiceImplTest {

    @Mock
    private PaymentRepository paymentRepository;

    @InjectMocks
    private PaymentService paymentService;

    @Test
    @DisplayName("Is the payment made as a result of the test, it is being checked")
    void savePaymentTest(){

        Payment payment = preparePaymentForUser();

        paymentRepository.save(payment);

        Mockito.verify(paymentRepository).save(any());
    }

    // we create a payment object
    private Payment preparePaymentForUser(){

        Payment payment = new Payment();
        payment.setUser(prepareUser());
        payment.setPaymentDate(getCurrentTime());
        payment.setSuccess(true);

        return payment;
    }

    private User prepareUser(){
        User user = new User(1L, UserType.INDIVIDUAL, "Enes", "enesdeniz@gmail.com","12345");
        return user;
    }

    //this function returns us the current time
    private LocalDate getCurrentTime() {

        LocalDate lt = LocalDate.now();
        return lt;
    }

}
