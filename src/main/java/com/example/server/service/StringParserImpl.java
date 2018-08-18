package com.example.server.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class StringParserImpl implements StringParser {

    @Override
    public Long getLongFromPattern(final String pattern) {
        final String cleanNumericPattern = pattern.replaceAll("[^\\d]", "");

        return StringUtils.isNotBlank(cleanNumericPattern) ?
                Long.parseLong(cleanNumericPattern) : 0L;
    }
}
