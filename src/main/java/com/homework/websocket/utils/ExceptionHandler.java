package com.homework.websocket.utils;

import com.homework.websocket.exception.TableNotFoundException;
import com.homework.websocket.model.Response;

import java.io.IOException;

public class ExceptionHandler {

    public static Response<String> handleException(Exception ex) {
        if (ex instanceof TableNotFoundException) {
            return new Response<>("Resource not found", ex.getMessage());
        }
        if (ex instanceof IllegalArgumentException) {
            return new Response<>("Wrong request format", ex.getMessage());
        }
        if (ex instanceof IOException) {
            return new Response<>("Wrong request format", "Please check your input");
        }
        return new Response<>("Failed to invoke command", "Try again later");
    }
}
