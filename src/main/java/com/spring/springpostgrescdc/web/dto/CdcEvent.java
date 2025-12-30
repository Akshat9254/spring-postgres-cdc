package com.spring.springpostgrescdc.web.dto;

public record CdcEvent<T>(
        T before,
        T after,
        String op,
        long tsMs
) {
}
