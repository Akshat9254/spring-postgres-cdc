package com.spring.springpostgrescdc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tools.jackson.databind.ObjectMapper;

@Configuration
class AppConfig {
    @Bean
    ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
