package com.gastrosfera.shared.v1.exception._4xx;


import com.gastrosfera.shared.v1.exception.BaseException;

public class BadRequestException extends BaseException {

    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public BadRequestException(String message) {
        this(message, null);
    }

}
