package com.homework.websocket.utils;

public class Assert {

    public static void notNull(Object object, String parameterName) {
        if (object == null) {
            throw new IllegalArgumentException(parameterName + " could not be null");
        }
    }

    public static void hasText(String text, String parameterName) {
        if (text == null || text.isEmpty()) {
            throw new IllegalArgumentException(parameterName + " could not be null or empty");
        }
    }
}
