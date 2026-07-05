package com.climalert.climalert.service.impl;

import com.climalert.climalert.model.Weather;
import com.climalert.climalert.repository.WeatherRepository;
import com.climalert.climalert.service.EmailService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AlertServiceImplTest {

    @Mock
    private WeatherRepository weatherRepository;

    @Mock
    private EmailService emailService;

    @InjectMocks
    private AlertServiceImpl alertService;

    @Test
    void deberiaEnviarMailCuandoElClimaEsCritico() {
        Weather weatherCritico = new Weather("Buenos Aires", 38.0, 70, "Sunny", LocalDateTime.now());
        when(weatherRepository.findLatest()).thenReturn(Optional.of(weatherCritico));

        alertService.evaluateAndNotify();

        verify(emailService).sendAlertEmail(weatherCritico);
    }

    @Test
    void noDeberiaEnviarMailCuandoElClimaNoEsCritico() {
        Weather weatherNormal = new Weather("Buenos Aires", 22.0, 45, "Sunny", LocalDateTime.now());
        when(weatherRepository.findLatest()).thenReturn(Optional.of(weatherNormal));

        alertService.evaluateAndNotify();

        verify(emailService, never()).sendAlertEmail(any());
    }

    @Test
    void noDeberiaEnviarMailCuandoNoHayMedicionesTodavia() {
        when(weatherRepository.findLatest()).thenReturn(Optional.empty());

        alertService.evaluateAndNotify();

        verify(emailService, never()).sendAlertEmail(any());
    }
}