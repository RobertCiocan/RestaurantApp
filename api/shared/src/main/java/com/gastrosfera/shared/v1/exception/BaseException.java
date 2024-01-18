package com.gastrosfera.shared.v1.exception;

import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

@Getter
public abstract class BaseException extends RuntimeException {

    private final UUID id;
    private final Instant timestamp;

    protected BaseException(String message, Throwable cause) {
        super(message, cause);
        this.id = UUID.randomUUID();
        this.timestamp = Instant.now();
    }

    protected BaseException(String message) {
        this(message, null);
    }
}
