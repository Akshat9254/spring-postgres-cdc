package com.spring.springpostgrescdc.web.exception;

public class TempUploadNotFoundException extends RuntimeException {
    public TempUploadNotFoundException(String uploadId) {
        super(String.format("UploadId %s not found: ", uploadId));
    }
}
