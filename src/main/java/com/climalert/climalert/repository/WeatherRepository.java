package com.climalert.climalert.repository;

import com.climalert.climalert.model.Weather;

import java.util.Optional;

public interface WeatherRepository {

    Weather save(Weather weather);

    Optional<Weather> findLatest();
}