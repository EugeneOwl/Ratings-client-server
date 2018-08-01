package com.example.server.service;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
public class RawDataProcessorImplTest {

    @TestConfiguration
    static class Config {

        @Bean
        RawDataProcessor rawDataProcessor() {

            return new RawDataProcessorImpl();
        }
    }

    private String rawString;

    @Autowired
    RawDataProcessor rawDataProcessor;

    @After
    public void tearDown() {
        rawString = "";
    }

    @Test
    public void getNumericList() {
        final List<Integer> expectedNumerics = Arrays.asList(1, 2, 3);

        rawString = "1 2 3";
        Assert.assertEquals(
                rawDataProcessor.getNumericList(rawString),
                expectedNumerics
        );

        rawString = "1, 2, 3.";
        Assert.assertEquals(
                rawDataProcessor.getNumericList(rawString),
                expectedNumerics
        );

        rawString = "1   2 , test3rubbish .";
        Assert.assertEquals(
                rawDataProcessor.getNumericList(rawString),
                expectedNumerics
        );

        rawString = "test empty dirty string with no numerals.";
        Assert.assertEquals(
                rawDataProcessor.getNumericList(rawString),
                new ArrayList<>()
        );
    }

    @Test
    public void getNumeric() {
        final int expectedNumeric = 5;

        rawString = "5 ";
        Assert.assertEquals(
                expectedNumeric,
                rawDataProcessor.getNumeric(rawString)
        );

        rawString = "  test5, rubbish";
        Assert.assertEquals(
                expectedNumeric,
                rawDataProcessor.getNumeric(rawString)
        );

        rawString = "  test, rubbish string without numeric";
        Assert.assertEquals(
                -1,
                rawDataProcessor.getNumeric(rawString)
        );
    }
}