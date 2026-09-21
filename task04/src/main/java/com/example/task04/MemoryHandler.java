package com.example.task04;

import java.util.ArrayList;
import java.util.List;

public class MemoryHandler implements MessageHandler {
    private final MessageHandler target;
    private final int bufferCapacity;
    private final List<String> buffer;

    public MemoryHandler(MessageHandler target, int bufferCapacity) {
        this.target = target;
        this.bufferCapacity = bufferCapacity;
        this.buffer = new ArrayList<>(bufferCapacity);
    }

    @Override
    public synchronized void handle(String formattedMessage) {
        buffer.add(formattedMessage);
        if (buffer.size() >= bufferCapacity) {
            flush();
        }
    }

    public synchronized void flush() {
        for (String msg : buffer) {
            target.handle(msg);
        }
        buffer.clear();
    }

    @Override
    public synchronized void close() throws Exception {
        flush();
        target.close();
    }
}