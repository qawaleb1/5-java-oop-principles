package com.example.task03;

/**
 * Класс, в котором собраны методы для работы с {@link TimeUnit}
 */
public class TimeUnitUtils {

    // --- Переводы в Milliseconds ---
    public static Milliseconds toMillis(Milliseconds src) {
        return new Milliseconds(src.toMillis());
    }

    public static Milliseconds toMillis(Seconds src) {
        return new Milliseconds(src.toMillis());
    }

    public static Milliseconds toMillis(Minutes src) {
        return new Milliseconds(src.toMillis());
    }

    public static Milliseconds toMillis(Hours src) {
        return new Milliseconds(src.toMillis());
    }

    // --- Переводы в Seconds ---
    public static Seconds toSeconds(Milliseconds src) {
        return new Seconds(src.toSeconds());
    }

    public static Seconds toSeconds(Seconds src) {
        return new Seconds(src.toSeconds());
    }

    public static Seconds toSeconds(Minutes src) {
        return new Seconds(src.toSeconds());
    }

    public static Seconds toSeconds(Hours src) {
        return new Seconds(src.toSeconds());
    }

    // --- Переводы в Minutes ---
    public static Minutes toMinutes(Milliseconds src) {
        return new Minutes(src.toMinutes());
    }

    public static Minutes toMinutes(Seconds src) {
        return new Minutes(src.toMinutes());
    }

    public static Minutes toMinutes(Minutes src) {
        return new Minutes(src.toMinutes());
    }

    public static Minutes toMinutes(Hours src) {
        return new Minutes(src.toMinutes());
    }

    // --- Переводы в Hours ---
    public static Hours toHours(Milliseconds src) {
        return new Hours(src.getHours());
    }

    public static Hours toHours(Seconds src) {
        return new Hours(src.getHours());
    }

    public static Hours toHours(Minutes src) {
        return new Hours(src.getHours());
    }

    public static Hours toHours(Hours src) {
        return new Hours(src.getHours());
    }
}