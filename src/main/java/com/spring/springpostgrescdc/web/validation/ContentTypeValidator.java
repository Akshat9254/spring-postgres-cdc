package com.spring.springpostgrescdc.web.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ContentTypeValidator implements ConstraintValidator<ValidContentType, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && UploadContentType.isValid(value);
    }
}
