package com.example.task04;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

public class FileHandler implements MessageHandler {
    private final Path filePath;
    private BufferedWriter writer;

    public FileHandler(Path filePath) {
        this.filePath = filePath;
    }

    private synchronized void ensureWriter() throws IOException {
        if (writer == null) {
            writer = new BufferedWriter(new FileWriter(filePath.toFile(), StandardCharsets.UTF_8, true));
        }
    }

    @Override
    public synchronized void handle(String formattedMessage) {
        try {
            ensureWriter();
            writer.write(formattedMessage);
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            System.err.println("Ошибка записи в файл: " + e.getMessage());
        }
    }

    @Override
    public synchronized void close() throws Exception {
        if (writer != null) {
            writer.close();
            writer = null;
        }
    }
}