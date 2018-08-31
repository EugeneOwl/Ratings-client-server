package com.example.server.service;

public interface StringParser {

    /**
     * Returns numeric long from string or 0 if string does not contain numeric.
     *
     * @param pattern
     * @return numeric from string or 0 if string does not contain numeric.
     */
    Long getLongFromPattern(final String pattern);
}
