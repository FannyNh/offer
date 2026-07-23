package com.thirdmoira.offer_backend.data.exceptions;

public class ServiceNotFoundException extends RuntimeException {
    public ServiceNotFoundException(String message) {
        super(message);
    }
}
