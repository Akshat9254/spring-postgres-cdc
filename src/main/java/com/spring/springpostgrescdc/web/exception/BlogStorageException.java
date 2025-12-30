package com.spring.springpostgrescdc.web.exception;

public class BlogStorageException extends RuntimeException {
    public BlogStorageException(String s, Exception e) {
        super(s, e);
    }
}
