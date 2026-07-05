package com.climalert.climalert.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record WeatherApiResponse(
        Location location,
        Current current
) {

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Location(
            String name
    ) {
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Current(
            double temp_c,
            int humidity,
            Condition condition
    ) {
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Condition(
            String text
    ) {
    }
}