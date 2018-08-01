package com.example.server.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class RawDataProcessorImpl implements RawDataProcessor {
    @Override
    public List<Integer> getNumericList(final String rawNumbers) {
        final List<Integer> list = new ArrayList<>();
        final Scanner scanner = new Scanner(cleanNumericString(rawNumbers));
        while (scanner.hasNextInt()) {
            list.add(scanner.nextInt());
        }

        return list;
    }

    @Override
    public int getNumeric(final String rawNumber) {
        final Scanner scanner = new Scanner(cleanNumericString(rawNumber));
        if (scanner.hasNextInt()) {
            return scanner.nextInt();
        }

        return -1;
    }

    private String cleanNumericString(final String numericString) {
        return numericString.replaceAll("[^0-9]", RawDataProcessor.delimiter);
    }
}
