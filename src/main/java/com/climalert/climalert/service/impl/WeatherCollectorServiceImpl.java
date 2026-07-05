package com.climalert.climalert.service.impl;

import com.climalert.climalert.client.WeatherApiClient;
import com.climalert.climalert.exceptions.WeatherApiException;
import com.climalert.climalert.model.Weather;
import com.climalert.climalert.repository.WeatherRepository;
import com.climalert.climalert.service.WeatherCollectorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class WeatherCollectorServiceImpl implements WeatherCollectorService {

    private final WeatherApiClient weatherApiClient;
    private final WeatherRepository weatherRepository;

    public WeatherCollectorServiceImpl(WeatherApiClient weatherApiClient,
                                       WeatherRepository weatherRepository) {
        this.weatherApiClient = weatherApiClient;
        this.weatherRepository = weatherRepository;
    }

    @Override
    public void collectAndStoreWeather() {
        try {
            Weather weather = weatherApiClient.getCurrentWeather();
            weatherRepository.save(weather);
            log.info("Clima recolectado y guardado: {}", weather);
        } catch (WeatherApiException e) {
            log.error("No se pudo recolectar el clima", e);
        }
    }
}