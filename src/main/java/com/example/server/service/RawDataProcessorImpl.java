package com.example.server.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class RawDataProcessorImpl implements RawDataProcessor {
    @Override
    public List<Integer> getNumericList(String rawNumbers) {
        List<Integer> list = new ArrayList<>();
        Scanner scanner = new Scanner(cleanNumericString(rawNumbers));
        while (scanner.hasNextInt()) {
            list.add(scanner.nextInt());
        }

        return list;
    }

    @Override
    public int getNumeric(String rawNumber) {
        Scanner scanner = new Scanner(cleanNumericString(rawNumber));
        if (scanner.hasNextInt()) {
            return scanner.nextInt();
        }

        return -1;
    }

    private String cleanNumericString(String numericString) {
        return numericString.replaceAll("[^0-9]", RawDataProcessor.delimiter);
    }
}
