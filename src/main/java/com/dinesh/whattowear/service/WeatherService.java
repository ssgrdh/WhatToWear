package com.dinesh.whattowear.service;

import com.dinesh.whattowear.model.WeatherResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URL;

@Service
public class WeatherService {


    @Value("${OPENWEATHER.API.KEY}")
    private String API_KEY;

    private RestTemplate restTemplate;

    public WeatherService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public WeatherResponse weatherData(Double lat, Double lng) {
        String url = "https://api.openweathermap.org/data/2.5/forecast?lat=" + lat.toString() + "&lon=" + lng.toString() + "&units=metric&appid=" + API_KEY;
        System.out.println(url);
        URI uri = URI.create(url);
        WeatherResponse weatherResponse = restTemplate.getForObject(uri, WeatherResponse.class);
        System.out.println("hi");
//        System.out.println(restTemplateForObject);
//        System.out.println(weatherResponse);
        return weatherResponse;
    }
}
