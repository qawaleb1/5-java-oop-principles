package com.example.task04;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Logger implements AutoCloseable {
    public enum Level { INFO, WARN, ERROR }

    private final List<MessageHandler> handlers = new CopyOnWriteArrayList<>();
    private final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    public Logger addHandler(MessageHandler handler) {
        handlers.add(handler);
        return this;
    }

    public void removeHandler(MessageHandler handler) {
        handlers.remove(handler);
    }

    public void log(Level level, String message) {
        String timestamp = LocalDateTime.now().format(timeFormatter);
        String formatted = String.format("[%s] [%s] %s", timestamp, level, message);

        for (MessageHandler handler : handlers) {
            handler.handle(formatted);
        }
    }

    public void info(String message)  { log(Level.INFO, message); }
    public void warn(String message)  { log(Level.WARN, message); }
    public void error(String message) { log(Level.ERROR, message); }

    @Override
    public void close() {
        for (MessageHandler handler : handlers) {
            try {
                handler.close();
            } catch (Exception e) {
                System.err.println("Ошибка при закрытии handler: " + e.getMessage());
            }
        }
    }
}