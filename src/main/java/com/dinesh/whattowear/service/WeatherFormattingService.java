package com.dinesh.whattowear.service;
import com.dinesh.whattowear.model.WeatherForecast;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@Service
public class WeatherFormattingService {

    // Method to format the weather forecast data into morning, afternoon, and evening
    public Map<String, Map<String, List<WeatherForecast>>> formatWeatherForecast(List<WeatherForecast> forecasts) {
        Map<String, Map<String, List<WeatherForecast>>> formattedForecasts = new LinkedHashMap<>();

        // Convert and group the forecasts by day and time period (morning, afternoon, evening)
        for (WeatherForecast forecast : forecasts) {
            // Convert Unix timestamp to LocalDateTime
            LocalDateTime dateTime = Instant.ofEpochSecond(forecast.dt())
                    .atZone(ZoneId.of("UTC"))
                    .toLocalDateTime();

            // Determine the part of the day (morning, afternoon, evening)
            String partOfDay = getPartOfDay(dateTime);

            // Format the date as "yyyy-MM-dd"
            String date = dateTime.toLocalDate().toString();

            // Initialize the date map if not present
            formattedForecasts.putIfAbsent(date, new LinkedHashMap<>());

            // Initialize the part of the day list if not present
            formattedForecasts.get(date).putIfAbsent(partOfDay, new ArrayList<>());

            // Add the forecast to the corresponding day and time period
            formattedForecasts.get(date).get(partOfDay).add(forecast);
        }

        return formattedForecasts;
    }

    // Helper method to determine the part of the day (morning, afternoon, evening)
    private String getPartOfDay(LocalDateTime dateTime) {
        int hour = dateTime.getHour();

        if (hour >= 6 && hour < 12) {
            return "Morning";
        } else if (hour >= 12 && hour < 18) {
            return "Afternoon";
        } else {
            return "Evening";
        }
    }

    // Method to format data into a string for GPT (or any AI model)
    public String formatForGPT(Map<String, Map<String, List<WeatherForecast>>> formattedForecasts) {
        StringBuilder builder = new StringBuilder();

        // Iterate through each day
        for (Map.Entry<String, Map<String, List<WeatherForecast>>> dayEntry : formattedForecasts.entrySet()) {
            String date = dayEntry.getKey();
            builder.append("Date: ").append(date).append("\n");

            // Iterate through each part of the day (morning, afternoon, evening)
            for (Map.Entry<String, List<WeatherForecast>> partOfDayEntry : dayEntry.getValue().entrySet()) {
                String partOfDay = partOfDayEntry.getKey();
                List<WeatherForecast> forecasts = partOfDayEntry.getValue();

                builder.append(partOfDay).append(":\n");

                // Add forecast details for each period
                for (WeatherForecast forecast : forecasts) {
                    builder.append("  Temp: ").append(forecast.main().temp())
                            .append("°C, Feels like: ").append(forecast.main().feels_like())
                            .append("°C, Weather: ").append(forecast.weather().getFirst().description())
                            .append(", Clouds: ").append(forecast.clouds().all()).append("%")
                            .append("\n");
                }
            }

            builder.append("\n");
        }

        return builder.toString();
    }
}