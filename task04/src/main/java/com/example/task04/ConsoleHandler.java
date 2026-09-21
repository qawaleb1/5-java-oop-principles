package com.example.task04;

public class ConsoleHandler implements MessageHandler {
    @Override
    public void handle(String formattedMessage) {
        System.out.println(formattedMessage);
    }
}