package org.turkcell.logger;

public abstract class BaseLogger implements Logger {
    public void logMessage(String message) {
        System.out.println("Log Message: Log Start" + "\n");
        log("Log Message: " + message + "\n");
        System.out.println("Log Message: Log End" + "\n");
    }
}
