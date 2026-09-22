package com.thirdmoira.offer_backend.data.exceptions;

public class ServiceCategoryNotFoundException extends RuntimeException {
    public ServiceCategoryNotFoundException(String message) {
        super(message);
    }
}
