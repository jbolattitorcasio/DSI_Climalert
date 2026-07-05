package com.climalert.climalert.client;

import com.climalert.climalert.model.Weather;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class WeatherApiClientImplTest {

    @Autowired
    private WeatherApiClient weatherApiClient;

    @Test
    void deberiaTraerElClimaActualDeWeatherApi() {
        Weather weather = weatherApiClient.getCurrentWeather();

        assertNotNull(weather);
        assertNotNull(weather.getLocation());
        assertTrue(weather.getTemperatureC() > -50 && weather.getTemperatureC() < 60);

        System.out.println("Clima obtenido: " + weather);
    }
}