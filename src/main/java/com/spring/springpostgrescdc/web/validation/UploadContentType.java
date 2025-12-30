package com.spring.springpostgrescdc.web.validation;

import java.util.Arrays;

public enum UploadContentType {
    MARKDOWN("text/markdown");

    private final String value;

    UploadContentType(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

    public static boolean isValid(String v) {
        return Arrays.stream(values())
                .anyMatch(t -> t.value.equals(v));
    }
}
