package com.climalert.climalert.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Weather {

    private static final double CRITICAL_TEMPERATURE = 35.0;
    private static final int CRITICAL_HUMIDITY = 60;

    private String location;
    private double temperatureC;
    private int humidity;
    private String condition;
    private LocalDateTime fetchedAt;

    public boolean isCritical() {
        return temperatureC > CRITICAL_TEMPERATURE && humidity > CRITICAL_HUMIDITY;
    }
}