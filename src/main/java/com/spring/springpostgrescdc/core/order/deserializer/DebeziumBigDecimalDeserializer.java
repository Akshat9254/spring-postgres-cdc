package com.spring.springpostgrescdc.core.order.deserializer;

import tools.jackson.core.JacksonException;
import tools.jackson.core.JsonParser;
import tools.jackson.databind.DeserializationContext;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.deser.std.StdDeserializer;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Base64;

public class DebeziumBigDecimalDeserializer extends StdDeserializer<BigDecimal> {
    protected DebeziumBigDecimalDeserializer() {
        super(BigDecimal.class);
    }

    @Override
    public BigDecimal deserialize(JsonParser p, DeserializationContext ctxt) throws JacksonException {
        JsonNode node = p.readValueAsTree();
        int scale = node.get("scale").asInt();
        byte[] decoded = Base64.getDecoder().decode(node.get("value").asText());
        return new BigDecimal(new BigInteger(decoded), scale);
    }
}
