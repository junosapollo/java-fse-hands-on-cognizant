package com.cognizant.testinglab.junit;

public class PerformanceTester {
    public long performTask(int iterations) {
        long value = 0;
        for (int i = 0; i < iterations; i++) {
            value += i;
        }
        return value;
    }
}
