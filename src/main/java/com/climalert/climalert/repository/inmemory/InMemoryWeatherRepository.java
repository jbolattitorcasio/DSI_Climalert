package com.climalert.climalert.repository.inmemory;

import com.climalert.climalert.model.Weather;
import com.climalert.climalert.repository.WeatherRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryWeatherRepository implements WeatherRepository {
    // Otra opción era usar: private final List<Weather> history = new CopyOnWriteArrayList<>();

    private final List<Weather> history = new ArrayList<>();

    @Override
    public synchronized Weather save(Weather weather) {
        history.add(weather);
        return weather;
    }

    @Override
    public synchronized Optional<Weather> findLatest() {
        return history.stream()
                .max(Comparator.comparing(Weather::getFetchedAt));
    }
}