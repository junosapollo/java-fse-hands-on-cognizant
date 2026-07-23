package com.cognizant.testinglab.logging;

import org.junit.jupiter.api.Test;

class LoggingExampleTest {
    @Test
    void emitsErrorWarningAndParameterizedMessages() {
        new LoggingExample().logLevels("tester", 2);
    }
}
