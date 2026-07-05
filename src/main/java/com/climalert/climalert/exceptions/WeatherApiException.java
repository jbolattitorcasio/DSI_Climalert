package com.climalert.climalert.exceptions;

public class WeatherApiException extends RuntimeException {

    public WeatherApiException(String message, Throwable cause) {
        super(message, cause);
    }
}