package com.gastrosfera.shared.v1.serialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.time.Instant;

@Configuration
public class SerializationConfig {

    @Bean
    @Primary
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        objectMapper.registerModule(javaTimeModule);
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        SimpleModule instantParserModule = new SimpleModule("InstantParser");
        instantParserModule.addSerializer(Instant.class, new DateTimeSerializer(Instant.class));
        instantParserModule.addDeserializer(Instant.class, new DateTimeDeserializer(Instant.class));
        objectMapper.registerModule(instantParserModule);
        return objectMapper;
    }

}
