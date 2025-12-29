package com.spring.springpostgrescdc;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record Order(
        @JsonProperty("id")
        long id,

        @JsonProperty("user_id")
        int userId,

        @JsonProperty("amount")
        BigDecimal amount,

        @JsonProperty("status")
        String status,

        @JsonProperty("created_at")
        OffsetDateTime createdAt
) {
}
