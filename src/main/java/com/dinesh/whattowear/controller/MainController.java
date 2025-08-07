package com.dinesh.whattowear.controller;

import com.dinesh.whattowear.business.ClothingSuggestion;
import com.dinesh.whattowear.model.WeatherForecast;
import com.dinesh.whattowear.model.WeatherResponse;
import com.dinesh.whattowear.service.WeatherFormattingService;
import com.dinesh.whattowear.service.WeatherService;
import org.apache.catalina.WebResourceRoot;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class MainController {

    private WeatherService weatherService;
    private WeatherFormattingService weatherFormattingService;
    private ClothingSuggestion clothingSuggestion;


    public MainController(WeatherService weatherService, WeatherFormattingService weatherFormattingService, ClothingSuggestion clothingSuggestion) {
        this.weatherService = weatherService;
        this.weatherFormattingService = weatherFormattingService;
        this.clothingSuggestion = clothingSuggestion;

    }


    @GetMapping("/whatToWear")
    public String getWhatToWear(@RequestParam double lat, @RequestParam double lon){
        // we need to get the data
        System.out.println("lat=" + lat + ", lng=" + lon);
        WeatherResponse weatherResponse = weatherService.weatherData(lat,lon);
        Map<String, Map<String, List<WeatherForecast>>> formattedForecast = weatherFormattingService.formatWeatherForecast(weatherResponse.list());
        System.out.println("Formatted the response of size " + formattedForecast.size());
        String formattedForGPT = weatherFormattingService.formatForGPT(formattedForecast);
        return clothingSuggestion.whatToWear(formattedForGPT);
    }

}
