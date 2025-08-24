package com.thirdmoira.offer_backend.data.exceptions;

public class NameAlreadyExistException extends RuntimeException {

    public NameAlreadyExistException(String message) {
        super(message);
    }
}
