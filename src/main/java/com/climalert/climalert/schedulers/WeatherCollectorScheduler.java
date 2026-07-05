package com.climalert.climalert.schedulers;

import com.climalert.climalert.service.WeatherCollectorService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class WeatherCollectorScheduler {

    private final WeatherCollectorService weatherCollectorService;

    public WeatherCollectorScheduler(WeatherCollectorService weatherCollectorService) {
        this.weatherCollectorService = weatherCollectorService;
    }

    @Scheduled(fixedRate = 300000) // 5 minutos
    public void run() {
        weatherCollectorService.collectAndStoreWeather();
    }
}