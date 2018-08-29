package com.example.server.security.exception;

public class ForbiddenOperationException extends RuntimeException {
    public ForbiddenOperationException(final String msg) {
        super(msg);
    }
}
