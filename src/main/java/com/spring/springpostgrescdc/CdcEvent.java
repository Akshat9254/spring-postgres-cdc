package com.spring.springpostgrescdc;

public record CdcEvent<T>(
        T before,
        T after,
        String op,
        long tsMs
) {
}
