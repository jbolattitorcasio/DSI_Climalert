package com.climalert.climalert.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Weather {
    private String location;
    private double temperatureC;
    private int humidity;
    private String condition;
    private LocalDateTime fetchedAt;
}
