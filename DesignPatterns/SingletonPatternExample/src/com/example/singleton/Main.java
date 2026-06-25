package com.example.singleton;

public class Main {
    public static void main(String[] args) {
        // Attempt to get the logger instance
        Logger logger1 = Logger.getInstance();
        Logger logger2 = Logger.getInstance();

        // Verify that both references point to the same object
        if (logger1 == logger2) {
            System.out.println("Singleton pattern is working correctly. Both variables contain the same instance.");
        } else {
            System.out.println("Singleton pattern failed. Variables contain different instances.");
        }

        // Test logging
        logger1.log("Application started.");
        logger2.log("Performing some tasks...");
    }
}
