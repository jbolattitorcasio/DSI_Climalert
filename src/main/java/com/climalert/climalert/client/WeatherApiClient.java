package com.climalert.climalert.client;

import com.climalert.climalert.model.Weather;

public interface WeatherApiClient {

    Weather getCurrentWeather();
}