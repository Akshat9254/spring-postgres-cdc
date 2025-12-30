package com.spring.springpostgrescdc.web.exception;

public class PresignedUrlGenerationException extends RuntimeException {
    public PresignedUrlGenerationException(String message, Throwable cause) {
        super(message, cause);
    }
}
