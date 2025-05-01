package com.example.demo.config;

public class LoggerService {
    // The static variable to hold the single instance
    private static LoggerService instance;

    // Private constructor to prevent instantiation from outside
    private LoggerService() {
        // This constructor is intentionally empty to prevent other classes from creating instances
    }

    // Public method to provide access to the single instance
    public static synchronized LoggerService getInstance() {
        if (instance == null) {
            instance = new LoggerService();
        }
        return instance;
    }

    // Example logging method
    public void log(String message) {
        System.out.println("[LOG] " + message);
    }
}
