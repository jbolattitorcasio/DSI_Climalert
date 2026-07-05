package com.climalert.climalert.client.impl;

import com.climalert.climalert.client.WeatherApiClient;
import com.climalert.climalert.dto.WeatherApiResponse;
import com.climalert.climalert.exceptions.WeatherApiException;
import com.climalert.climalert.model.Weather;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Component
public class WeatherApiClientImpl implements WeatherApiClient {

    private final RestTemplate restTemplate;

    @Value("${weather.api.key}")
    private String apiKey;

    @Value("${weather.api.url}")
    private String apiUrl;

    @Value("${weather.api.location}")
    private String location;

    public WeatherApiClientImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Weather getCurrentWeather() {
        String url = String.format("%s?key=%s&q=%s", apiUrl, apiKey, location);
        try {
            WeatherApiResponse response = restTemplate.getForObject(url, WeatherApiResponse.class);
            return traducir(response);
        } catch (RestClientException e) {
            throw new WeatherApiException("No se pudo obtener el clima desde WeatherAPI", e);
        }
    }

    private Weather traducir(WeatherApiResponse response) {
        return new Weather(
                response.location().name(),
                response.current().temp_c(),
                response.current().humidity(),
                response.current().condition().text(),
                LocalDateTime.now()
        );
    }
}