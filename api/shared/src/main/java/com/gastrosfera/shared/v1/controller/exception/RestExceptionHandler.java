package com.gastrosfera.shared.v1.controller.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gastrosfera.shared.v1.exception.BaseException;
import com.gastrosfera.shared.v1.exception._4xx.BadRequestException;
import com.gastrosfera.shared.v1.exception._4xx.EntityAlreadyExistsException;
import com.gastrosfera.shared.v1.exception._4xx.EntityDoesNotExistException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;
import java.util.UUID;
import java.util.stream.Collectors;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    private final ExceptionMapper exceptionMapper;
    private final HttpServletRequest request;


    public RestExceptionHandler(@Value("${exception.handler.exclude-fields:none}") String excludeFields, ObjectMapper objectMapper, HttpServletRequest request) {
        this.exceptionMapper = new RestExceptionMapper(excludeFields, objectMapper, request);
        this.request = request;
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return super.handleHttpRequestMethodNotSupported(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return super.handleHttpMediaTypeNotSupported(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return super.handleHttpMediaTypeNotAcceptable(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return super.handleMissingPathVariable(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return super.handleMissingServletRequestParameter(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleServletRequestBindingException(ServletRequestBindingException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return super.handleServletRequestBindingException(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleConversionNotSupported(ConversionNotSupportedException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return super.handleConversionNotSupported(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return super.handleTypeMismatch(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return super.handleHttpMessageNotReadable(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotWritable(HttpMessageNotWritableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return super.handleHttpMessageNotWritable(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ErrorDTO.ConstraintValidationErrorDTO dto = new ErrorDTO.ConstraintValidationErrorDTO(
                UUID.randomUUID().toString(),
                Instant.now(),
                ex.getMessage(),
                null,
                ex.getClass().getSimpleName(),
                this.request.getRequestURI(),
                ex.getFieldErrors().stream().map(fieldError -> new ErrorDTO.ValidationErrorDTO(fieldError.getField(), fieldError.getDefaultMessage(), fieldError.getRejectedValue())).collect(Collectors.toSet())
        );
        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestPart(MissingServletRequestPartException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return super.handleMissingServletRequestPart(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return super.handleNoHandlerFoundException(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleAsyncRequestTimeoutException(AsyncRequestTimeoutException ex, HttpHeaders headers, HttpStatusCode status, WebRequest webRequest) {
        return super.handleAsyncRequestTimeoutException(ex, headers, status, webRequest);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return handleException(ex, body, headers, status, request);
    }

    private <T extends Exception> ResponseEntity<Object> handleException(T ex, Object body, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        if (ex instanceof BaseException) {
            return handleExceptionInternal((BaseException) ex, request);
        } else if (ex instanceof MethodArgumentNotValidException) {
            return handleExceptionInternal((MethodArgumentNotValidException) ex, request);
        } else if (ex instanceof RuntimeException) {
            return handleExceptionInternal((RuntimeException) ex, request);
        }
        return handleExceptionInternal(ex, request);
    }

    @ExceptionHandler(value = { BadRequestException.class })
    protected ResponseEntity<Object> handleExceptionInternal(BadRequestException e, WebRequest request) {
        return exceptionMapper.map(e, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(value = { EntityDoesNotExistException.class })
    protected ResponseEntity<Object> handleExceptionInternal(EntityDoesNotExistException e, WebRequest request) {
        return exceptionMapper.map(e, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(value = { EntityAlreadyExistsException.class })
    protected ResponseEntity<Object> handleExceptionInternal(EntityAlreadyExistsException e, WebRequest request) {
        return exceptionMapper.map(e, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = { BaseException.class })
    protected ResponseEntity<Object> handleExceptionInternal(BaseException e, WebRequest request) {
        return exceptionMapper.map(e, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = { Exception.class })
    protected ResponseEntity<Object> handleExceptionInternal(Exception e, WebRequest request) {
        ErrorDTO dto = ErrorDTO.builder()
                .id(UUID.randomUUID().toString())
                .timestamp(Instant.now())
                .exceptionName(e.getClass().getSimpleName())
                .build();
        return new ResponseEntity<>(dto, HttpStatus.INTERNAL_SERVER_ERROR);
    }



}
