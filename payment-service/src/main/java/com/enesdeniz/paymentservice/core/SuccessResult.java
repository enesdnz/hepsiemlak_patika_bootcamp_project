package com.enesdeniz.paymentservice.core;

public class SuccessResult extends Result {

    public SuccessResult(String message) {
        super(true, message);
    }
}
