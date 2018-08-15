package com.example.server.security.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@ControllerAdvice
class GlobalDefaultExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ResponseEntity<Object> handleMethodArgumentNotValid(
            final HttpServletRequest req,
            final MethodArgumentNotValidException e
    ) {
        writeDefaultErrorLogMessage(req.getRequestURL(), e.getMessage());
        return new ResponseEntity<>(e.getBindingResult().getFieldErrors()
                .stream().map(FieldError::getDefaultMessage),
                HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> handleEntityNotFound(
            final HttpServletRequest req,
            final EntityNotFoundException e
    ) {
        writeDefaultErrorLogMessage(req.getRequestURL(), e.getMessage());
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    // When deleting by not existing id.
    @ExceptionHandler(EmptyResultDataAccessException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> handleEmptyResultDataAccess(
            final HttpServletRequest req,
            final EmptyResultDataAccessException e
    ) {
        writeDefaultErrorLogMessage(req.getRequestURL(), e.getMessage());
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    private void writeDefaultErrorLogMessage(final StringBuffer url, final String errorMessage) {
        log.error("Request {} raised {}.", url, errorMessage);
    }
}