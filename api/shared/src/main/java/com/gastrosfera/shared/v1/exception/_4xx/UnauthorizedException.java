package com.gastrosfera.shared.v1.exception._4xx;


import com.gastrosfera.shared.v1.exception.BaseException;

public class UnauthorizedException extends BaseException {

    public UnauthorizedException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnauthorizedException(String message) {
        this(message, null);
    }
}
