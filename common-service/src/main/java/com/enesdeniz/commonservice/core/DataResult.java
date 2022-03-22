package com.enesdeniz.commonservice.core;

public class DataResult<T> extends Result {
    private final T data;

    public DataResult(boolean success, String message, T data) {
        super(success, message);
        this.data = data;
    }

    public T getData() {
        return data;
    }
}
