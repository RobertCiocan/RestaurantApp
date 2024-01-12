package com.gastrosfera.shared.v1.controller.exception;

import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.Set;

@Getter
@Setter
@Builder
@JsonFilter("propertyFilter")
public class ErrorDTO {

    private String id;
    private Instant timestamp;
    private String message;
    private Object exception;
    private String exceptionName;
    private String path;

    @Getter
    @AllArgsConstructor
    static class ValidationErrorDTO {
        private String field;
        private String message;
        private Object rejectedValue;
    }

    @Getter
    static class ConstraintValidationErrorDTO extends ErrorDTO {

        Set<ValidationErrorDTO> constraintViolations;

        public ConstraintValidationErrorDTO(String id, Instant timestamp, String message, Object exception, String exceptionName, String path) {
            super(id, timestamp, message, exception, exceptionName, path);
        }
        public ConstraintValidationErrorDTO(String id, Instant timestamp, String message, Object exception, String exceptionName, String path, Set<ValidationErrorDTO> constraintValidationErrorDTOS) {
            this(id, timestamp, message, exception, exceptionName, path);
            this.constraintViolations = constraintValidationErrorDTOS;
        }

    }

}
