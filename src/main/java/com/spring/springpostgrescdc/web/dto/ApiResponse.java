package com.spring.springpostgrescdc.web.dto;

import java.time.Instant;

public record ApiResponse<T>(
        boolean success,
        String message,
        Instant timestamp,
        T data
) {
    public ApiResponse(boolean success, String message, T data) {
        this(success, message, Instant.now(), data);
    }
}
