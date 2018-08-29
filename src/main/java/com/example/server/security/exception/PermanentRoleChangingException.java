package com.example.server.security.exception;

import org.springframework.beans.NotWritablePropertyException;

public class PermanentRoleChangingException extends Exception {
    public PermanentRoleChangingException(final String msg) {
        super(msg);
    }
}
