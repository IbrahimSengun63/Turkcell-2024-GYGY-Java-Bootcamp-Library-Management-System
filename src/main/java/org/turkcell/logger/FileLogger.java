package org.turkcell.logger;

public class FileLogger extends BaseLogger{
    @Override
    public void log(String message) {
        System.out.println("FileLogger: " + message);
    }
}
