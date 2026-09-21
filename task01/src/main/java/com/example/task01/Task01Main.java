package com.example.task01;

public class Task01Main {
    public static void main(String[] args) {
        Logger logger1 = Logger.getLogger("test");
        Logger logger2 = Logger.getLogger("test");
        System.out.println("Ссылки равны: " + (logger1 == logger2)); // true

        // 2. Логирование с форматированием и без
        Logger appLogger = Logger.getLogger("myLogger");
        appLogger.info("Application started");
        appLogger.warning("User %s attempted invalid action %d times", "admin", 3);

        // 3. Проверка фильтрации уровней
        appLogger.setLevel(Logger.LogLevel.WARNING);

        // Ниже WARNING — не выведется:
        appLogger.debug("Debug details: x = 10");
        appLogger.info("Standard info message");

        // WARNING и ERROR — выведутся:
        appLogger.warning("something weird happened");
        appLogger.error("Fatal exception code: %d", 500);
    }
}
