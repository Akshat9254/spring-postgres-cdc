package com.spring.springpostgrescdc.web.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = ContentTypeValidator.class)
@Target({ FIELD })
@Retention(RUNTIME)
public @interface ValidContentType {
    String message() default "Unsupported content type";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
