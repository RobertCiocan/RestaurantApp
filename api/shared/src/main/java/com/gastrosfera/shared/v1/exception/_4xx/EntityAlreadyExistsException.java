package com.gastrosfera.shared.v1.exception._4xx;


import com.gastrosfera.shared.v1.exception.BaseException;

public class EntityAlreadyExistsException extends BaseException {

    public EntityAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public EntityAlreadyExistsException(String message) {
        this(message, null);
    }
}
