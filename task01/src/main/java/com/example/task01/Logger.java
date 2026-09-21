package com.example.task01;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Logger {

    // Перечисление уровней важности в порядке возрастания
    public enum LogLevel {
        DEBUG,
        INFO,
        WARNING,
        ERROR
    }

    private static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");

    // Потокобезопасный кэш экземпляров логгеров по их именам
    private static final Map<String, Logger> LOGGERS = new ConcurrentHashMap<>();

    private final String name;
    private LogLevel currentLevel = LogLevel.DEBUG;

    // Закрытый конструктор, чтобы новые экземпляры создавались только через getLogger
    private Logger(String name) {
        this.name = name;
    }

    /**
     * Возвращает экземпляр логгера с указанным именем.
     * Повторные вызовы с тем же именем возвращают тот же экземпляр.
     */
    public static Logger getLogger(String name) {
        return LOGGERS.computeIfAbsent(name, Logger::new);
    }

    public String getName() {
        return name;
    }

    public LogLevel getLevel() {
        return currentLevel;
    }

    public void setLevel(LogLevel level) {
        this.currentLevel = level;
    }

    // --- Общие методы log ---

    public void log(LogLevel level, String message) {
        // Проверка: уровень сообщения должен быть >= текущего уровня логгера
        if (level.compareTo(this.currentLevel) >= 0) {
            String timestamp = LocalDateTime.now().format(DATE_TIME_FORMATTER);
            System.out.printf("[%s] %s %s - %s%n", level.name(), timestamp, name, message);
        }
    }

    public void log(LogLevel level, String format, Object... args) {
        if (level.compareTo(this.currentLevel) >= 0) {
            String formattedMessage = String.format(format, args);
            log(level, formattedMessage);
        }
    }

    // --- Методы для уровня DEBUG ---

    public void debug(String message) {
        log(LogLevel.DEBUG, message);
    }

    public void debug(String format, Object... args) {
        log(LogLevel.DEBUG, format, args);
    }

    // --- Методы для уровня INFO ---

    public void info(String message) {
        log(LogLevel.INFO, message);
    }

    public void info(String format, Object... args) {
        log(LogLevel.INFO, format, args);
    }

    // --- Методы для уровня WARNING ---

    public void warning(String message) {
        log(LogLevel.WARNING, message);
    }

    public void warning(String format, Object... args) {
        log(LogLevel.WARNING, format, args);
    }

    // --- Методы для уровня ERROR ---

    public void error(String message) {
        log(LogLevel.ERROR, message);
    }

    public void error(String format, Object... args) {
        log(LogLevel.ERROR, format, args);
    }
}