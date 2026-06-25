package com.example.singleton;

public class Logger {
    // private static instance of the same class
    private static Logger instance;

    // private constructor to restrict instantiation from other classes
    private Logger() {
        // initialization code eg opening a log file or setting up a database connection
    }

    // public static method that returns the instance of the class creating it if it doesnt exist
    public static synchronized Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    // example logging method
    public void log(String message) {
        System.out.println("[LOG] " + message);
    }
}
