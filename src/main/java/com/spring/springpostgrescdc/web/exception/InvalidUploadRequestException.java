package com.spring.springpostgrescdc.web.exception;

public class InvalidUploadRequestException extends RuntimeException {
    public InvalidUploadRequestException(String message) {
        super(message);
    }
}
