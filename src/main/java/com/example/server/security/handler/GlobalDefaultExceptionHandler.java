package com.example.server.security.handler;

import com.example.server.security.exception.ForbiddenOperationException;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.SQLGrammarException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.data.mapping.PropertyReferenceException;
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
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleMethodArgumentNotValid(
            final HttpServletRequest req,
            final MethodArgumentNotValidException e
    ) {
        writeDefaultErrorLogMessage(req.getRequestURL(), e.getMessage());
        return new ResponseEntity<>(e.getBindingResult().getFieldErrors()
                .stream().map(FieldError::getDefaultMessage),
                HttpStatus.BAD_REQUEST);
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

    @ExceptionHandler(PropertyReferenceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handlePropertyReference(
            final HttpServletRequest req,
            final PropertyReferenceException e
    ) {
        writeDefaultErrorLogMessage(req.getRequestURL(), e.getMessage());
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    // When sorting by not existing field.
    @ExceptionHandler(InvalidDataAccessApiUsageException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleInvalidDataAccessApiUsage(
            final HttpServletRequest req,
            final InvalidDataAccessApiUsageException e
    ) {
        writeDefaultErrorLogMessage(req.getRequestURL(), e.getMessage());
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    // When getting page request with negative page number.
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleIllegalArgument(
            final HttpServletRequest req,
            final IllegalArgumentException e
    ) {
        writeDefaultErrorLogMessage(req.getRequestURL(), e.getMessage());
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    // When sorting by not existing field.
    @ExceptionHandler(SQLGrammarException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleSQLGrammar(
            final HttpServletRequest req,
            final SQLGrammarException e
    ) {
        writeDefaultErrorLogMessage(req.getRequestURL(), e.getMessage());
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    // When try to update/remove permanent role
    @ExceptionHandler(ForbiddenOperationException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<Object> handleSQLGrammar(
            final HttpServletRequest req,
            final ForbiddenOperationException e
    ) {
        writeDefaultErrorLogMessage(req.getRequestURL(), e.getMessage());
        return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
    }

    private void writeDefaultErrorLogMessage(final StringBuffer url, final String errorMessage) {
        log.error("Request {} raised {}.", url, errorMessage);
    }
}