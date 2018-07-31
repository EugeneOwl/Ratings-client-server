package com.example.server.service;

import java.util.List;

public interface RawDataProcessor {

    String delimiter = " ";

    List<Integer> getNumericList(String rawNumbers);

    int getNumeric(String rawNumber);
}
