package com.gastrosfera.shared.v1.controller.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.gastrosfera.shared.v1.exception.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class ExceptionMapper {

    private final Set<String> excludeFields;

    private final ObjectMapper objectMapper;

    public ExceptionMapper(String excludeFields, ObjectMapper objectMapper) {
        this.excludeFields = excludeFields != null ? Arrays.stream(excludeFields.split(",")).collect(Collectors.toSet()) : new HashSet<>();
        FilterProvider filterProvider = new SimpleFilterProvider()
                .addFilter("propertyFilter", new SimpleBeanPropertyFilter.SerializeExceptFilter(this.excludeFields));
        objectMapper.setFilterProvider(filterProvider);
        this.objectMapper = objectMapper;
    }

    public <T extends BaseException> String toBody(T ex) throws JsonProcessingException {
        return objectMapper.writeValueAsString(this.map(ex));
    }

    protected abstract <T extends BaseException> ErrorDTO map(T e);
    protected abstract <T extends BaseException> ResponseEntity<Object> map(T e, HttpStatus httpStatus);


}
