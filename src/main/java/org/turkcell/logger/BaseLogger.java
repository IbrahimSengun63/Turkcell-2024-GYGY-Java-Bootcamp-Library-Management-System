package org.turkcell.logger;

public abstract class BaseLogger implements Logger{
    public void logMessage(String message) {
        System.out.println("Log Start");
        log(message);
        System.out.println("Log End");
    }
}
