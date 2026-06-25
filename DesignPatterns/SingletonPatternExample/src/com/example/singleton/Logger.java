package com.example.singleton;

public class Logger {
    // Private static instance of the same class
    private static Logger instance;

    // Private constructor to restrict instantiation from other classes
    private Logger() {
        // Initialization code, e.g., opening a log file or setting up a database connection
    }

    // Public static method that returns the instance of the class, creating it if it doesn't exist
    public static synchronized Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    // Example logging method
    public void log(String message) {
        System.out.println("[LOG] " + message);
    }
}
