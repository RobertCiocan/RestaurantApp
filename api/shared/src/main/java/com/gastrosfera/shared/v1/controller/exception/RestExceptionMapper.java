package com.gastrosfera.shared.v1.controller.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gastrosfera.shared.v1.exception.BaseException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.time.Instant;
import java.util.Arrays;
import java.util.UUID;
import java.util.stream.Collectors;

public class RestExceptionMapper extends ExceptionMapper {

    private final HttpServletRequest request;

    public RestExceptionMapper(String excludeFields, ObjectMapper objectMapper, HttpServletRequest request) {
        super(excludeFields, objectMapper);
        this.request = request;
    }

    public <T extends BaseException> ResponseEntity<Object> map(T e, HttpStatus httpStatus) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        try {
            return new ResponseEntity<>(this.toBody(e), headers, httpStatus);
        } catch (JsonProcessingException exception) {
            ErrorDTO dto = ErrorDTO.builder()
                    .id(UUID.randomUUID().toString())
                    .timestamp(Instant.now())
                    .exceptionName(e.getClass().getSimpleName())
                    .message("Could not serialize error")
                    .build();
            return new ResponseEntity<>(dto, headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    protected <T extends BaseException> ErrorDTO map(T e) {
        return ErrorDTO.builder()
                .id(e.getId().toString())
                .exceptionName(e.getClass().getSimpleName())
                .message(e.getMessage())
                .timestamp(e.getTimestamp())
                .path(request.getRequestURI())
                .exception(Arrays.stream(e.getStackTrace()).map(StackTraceElement::toString).collect(Collectors.joining())).build();
    }


}
