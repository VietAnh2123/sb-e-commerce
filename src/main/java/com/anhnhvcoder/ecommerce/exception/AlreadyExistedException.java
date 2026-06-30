package com.anhnhvcoder.ecommerce.exception;

public class AlreadyExistedException extends RuntimeException{
    public AlreadyExistedException(String message) {
        super(message);
    }
}
