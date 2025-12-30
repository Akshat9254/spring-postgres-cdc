package com.spring.springpostgrescdc.core.order;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderCdcListener {
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "pg.public.orders")
    public void onEvent(String event) {
        JsonNode root = objectMapper.readTree(event);
        JsonNode payload = root.get("payload");
        String op = payload.get("op").asString();

        if(op.equals("c")) {
            Order newOrder = objectMapper.treeToValue(payload.get("after"), Order.class);
            log.info("New order: {}", newOrder);
        }
    }
}
