package com.gastrosfera.shared.v1.exception._4xx;


import com.gastrosfera.shared.v1.exception.BaseException;

public class InvalidDataTypeException extends BaseException {

    public InvalidDataTypeException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidDataTypeException(String message) {
        this(message, null);
    }
}
