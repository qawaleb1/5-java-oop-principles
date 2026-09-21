package com.example.task04;

public interface MessageHandler extends AutoCloseable {
    void handle(String formattedMessage);

    @Override
    default void close() throws Exception {
        // По умолчанию закрытие не требуется
    }
}