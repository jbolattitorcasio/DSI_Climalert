package com.climalert.climalert.service.impl;

import com.climalert.climalert.client.WeatherApiClient;
import com.climalert.climalert.model.Weather;
import com.climalert.climalert.repository.WeatherRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WeatherCollectorServiceImplTest {

    @Mock
    private WeatherApiClient weatherApiClient;

    @Mock
    private WeatherRepository weatherRepository;

    @InjectMocks
    private WeatherCollectorServiceImpl weatherCollectorService;

    @Test
    void deberiaGuardarElClimaObtenidoDelProveedor() {
        Weather weatherSimulado = new Weather("Buenos Aires", 30.0, 50, "Sunny", LocalDateTime.now());
        when(weatherApiClient.getCurrentWeather()).thenReturn(weatherSimulado);

        weatherCollectorService.collectAndStoreWeather();

        verify(weatherRepository).save(weatherSimulado);
    }
}