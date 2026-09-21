package com.example.task04;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class RotationFileHandler implements MessageHandler {
    private final Path directory;
    private final String prefix;
    private final ChronoUnit rotationUnit;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss")
            .withZone(ZoneId.systemDefault());

    private Instant currentWindowStart;
    private BufferedWriter currentWriter;

    public RotationFileHandler(Path directory, String prefix, ChronoUnit rotationUnit) {
        this.directory = directory;
        this.prefix = prefix;
        this.rotationUnit = rotationUnit;
    }

    private synchronized void rotateIfNeeded() throws IOException {
        Instant now = Instant.now();
        Instant windowStart = now.truncatedTo(rotationUnit);

        if (currentWriter == null || !windowStart.equals(currentWindowStart)) {
            closeCurrentWriter();
            Files.createDirectories(directory);

            currentWindowStart = windowStart;
            String fileName = String.format("%s_%s.log", prefix, formatter.format(windowStart));
            Path logPath = directory.resolve(fileName);

            currentWriter = new BufferedWriter(new FileWriter(logPath.toFile(), StandardCharsets.UTF_8, true));
        }
    }

    private void closeCurrentWriter() throws IOException {
        if (currentWriter != null) {
            currentWriter.flush();
            currentWriter.close();
            currentWriter = null;
        }
    }

    @Override
    public synchronized void handle(String formattedMessage) {
        try {
            rotateIfNeeded();
            currentWriter.write(formattedMessage);
            currentWriter.newLine();
            currentWriter.flush();
        } catch (IOException e) {
            System.err.println("Ошибка ротации/записи лога: " + e.getMessage());
        }
    }

    @Override
    public synchronized void close() throws Exception {
        closeCurrentWriter();
    }
}