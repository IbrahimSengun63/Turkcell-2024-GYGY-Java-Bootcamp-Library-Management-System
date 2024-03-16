package org.turkcell.logger;

public class DatabaseLogger extends BaseLogger{
    @Override
    public void log(String message) {
        System.out.println("DatabaseLogger: " + message);
    }
}
