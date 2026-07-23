package com.cognizant.testinglab.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingExample {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingExample.class);

    public void logLevels(String user, int attempts) {
        LOGGER.error("This is an error message");
        LOGGER.warn("This is a warning message");
        LOGGER.info("User {} has made {} attempts", user, attempts);
    }

    public static void main(String[] args) {
        new LoggingExample().logLevels("demo", 1);
    }
}
