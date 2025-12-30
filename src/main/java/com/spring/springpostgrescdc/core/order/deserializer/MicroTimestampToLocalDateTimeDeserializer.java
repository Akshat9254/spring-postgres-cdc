package com.spring.springpostgrescdc.core.order.deserializer;

import tools.jackson.core.JacksonException;
import tools.jackson.core.JsonParser;
import tools.jackson.databind.DeserializationContext;
import tools.jackson.databind.deser.std.StdDeserializer;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class MicroTimestampToLocalDateTimeDeserializer extends StdDeserializer<LocalDateTime> {
    public MicroTimestampToLocalDateTimeDeserializer() {
        super(LocalDateTime.class);
    }

    @Override
    public LocalDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws JacksonException {
        long micros = p.getLongValue();

        Instant instant = Instant.ofEpochSecond(
                micros / 1_000_000,
                (micros % 1_000_000) * 1_000
        );

        return LocalDateTime.ofInstant(instant, ZoneOffset.UTC);
    }
}
