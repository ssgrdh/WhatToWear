package com.dinesh.whattowear.model;

import java.util.List;

// Root record
public record WeatherResponse(
        City city,
        List<WeatherForecast> list
) {}

