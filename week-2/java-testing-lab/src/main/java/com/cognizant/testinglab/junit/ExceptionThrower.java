package com.cognizant.testinglab.junit;

public class ExceptionThrower {
    public void throwException() {
        throw new IllegalStateException("expected test exception");
    }
}
