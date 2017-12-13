package com.ifood.backend.advancedtest.api.repo;

import com.ifood.backend.advancedtest.api.model.Weather;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherRepo {

    @Value("${services.weather.uri}")
    private String weatherUri;

    @Value("${services.weather.id}")
    private String weatherId;

    public Weather getWeatherInfo(String cityName) {
        String url = weatherUri.concat("?q=" + cityName + "&APPID=" + weatherId);
        return fetchWeatherAPI(url);
    }

    public Weather getWeatherInfo(float lat, float lon) {
        String url = weatherUri.concat("?lat=" + lat + "&lon=" + lon + "&APPID=" + weatherId);
        return fetchWeatherAPI(url);
    }

    private Weather fetchWeatherAPI(String url, String... parameters) {
        RestTemplate restTemplate = new RestTemplate();
        Weather weather = restTemplate.getForObject(url, Weather.class, parameters, weatherId);
        return weather;
    }

}
