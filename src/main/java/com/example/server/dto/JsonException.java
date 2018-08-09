package com.example.server.dto;

/**
 * @author ikatlinsky
 * @since 5/12/17
 */
public class JsonException extends RuntimeException {

    public JsonException(final String message) {
        super(message);
    }

    public JsonException(final String message, final Throwable exception) {
        super(message, exception);
    }
}
