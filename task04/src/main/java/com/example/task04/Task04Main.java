package com.example.task04;

import java.nio.file.Paths;
import java.time.temporal.ChronoUnit;

public class Task04Main {
    public static void main(String[] args) {
        try (Logger logger = new Logger()) {
            // 1. Вывод сразу в консоль
            logger.addHandler(new ConsoleHandler());

            // 2. Вывод в файл с часовой ротацией
            logger.addHandler(new RotationFileHandler(
                    Paths.get("./logs"),
                    "app",
                    ChronoUnit.HOURS
            ));

            // 3. Вывод пачками по 3 сообщения в отдельный файл через MemoryHandler
            FileHandler batchFileHandler = new FileHandler(Paths.get("./logs/batched.log"));
            MemoryHandler memoryHandler = new MemoryHandler(batchFileHandler, 3);
            logger.addHandler(memoryHandler);

            // Логирование событий
            logger.info("Запуск приложения");
            logger.warn("Обнаружено медленное соединение");
            logger.info("Обработка пакета данных..."); // MemoryHandler автоматически сбросит буфер (3-е сообщение)
            logger.error("Критический сбой сервиса");

            // Ручной сброс оставшихся в памяти логов (или произойдет автоматически в close())
            memoryHandler.flush();
        }
    }
}
