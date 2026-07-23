package com.cognizant.testinglab.calculator;

import org.springframework.stereotype.Service;

@Service
public class CalculatorService {
    public int add(int left, int right) {
        return left + right;
    }
}
