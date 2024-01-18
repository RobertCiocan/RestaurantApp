package com.gastrosfera.shared.v1.exception._4xx;


import com.gastrosfera.shared.v1.exception.BaseException;

public class EntityDoesNotExistException extends BaseException {

    public EntityDoesNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public EntityDoesNotExistException(String message) {
        this(message, null);
    }
}
