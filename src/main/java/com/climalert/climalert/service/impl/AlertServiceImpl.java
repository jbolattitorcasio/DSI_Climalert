package com.climalert.climalert.service.impl;

import com.climalert.climalert.model.Weather;
import com.climalert.climalert.repository.WeatherRepository;
import com.climalert.climalert.service.AlertService;
import com.climalert.climalert.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class AlertServiceImpl implements AlertService {

    private final WeatherRepository weatherRepository;
    private final EmailService emailService;

    public AlertServiceImpl(WeatherRepository weatherRepository, EmailService emailService) {
        this.weatherRepository = weatherRepository;
        this.emailService = emailService;
    }

    @Override
    public void evaluateAndNotify() {
        Optional<Weather> latest = weatherRepository.findLatest();

        if (latest.isEmpty()) {
            log.info("Todavía no hay mediciones para analizar.");
            return;
        }

        Weather weather = latest.get();

        if (weather.isCritical()) {
            log.warn("Condición climática crítica detectada: {}", weather);
            emailService.sendAlertEmail(weather);
        } else {
            log.info("Sin condiciones críticas. Último registro: {}", weather);
        }
    }
}