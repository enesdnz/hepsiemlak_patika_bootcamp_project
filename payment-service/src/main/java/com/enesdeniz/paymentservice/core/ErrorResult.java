package com.enesdeniz.paymentservice.core;

public class ErrorResult extends Result {

    public ErrorResult(boolean success, String message) {
        super(false, message);
    }
}
