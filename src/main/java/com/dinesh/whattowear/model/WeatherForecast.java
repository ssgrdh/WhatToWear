package com.dinesh.whattowear.model;

import java.util.List;

// Nested record for WeatherForecast
public record WeatherForecast(
        Main main,
        List<Weather> weather,
        Clouds clouds,
        long dt
) {}
