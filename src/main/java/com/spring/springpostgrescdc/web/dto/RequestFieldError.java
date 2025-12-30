package com.spring.springpostgrescdc.web.dto;

import org.springframework.validation.FieldError;

public record RequestFieldError(
        String field,
        String message
) {
    public static RequestFieldError fromFieldError(FieldError fieldError) {
        return new RequestFieldError(fieldError.getField(), fieldError.getDefaultMessage());
    }
}
