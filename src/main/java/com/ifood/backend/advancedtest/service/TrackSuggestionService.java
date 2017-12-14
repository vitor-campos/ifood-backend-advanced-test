package com.ifood.backend.advancedtest.service;

import com.ifood.backend.advancedtest.api.model.Category;
import com.ifood.backend.advancedtest.api.model.Weather;
import com.ifood.backend.advancedtest.api.repo.SpotifyRepo;
import com.ifood.backend.advancedtest.api.repo.WeatherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class TrackSuggestionService {

    @Autowired
    SpotifyRepo spotifyRepo;

    @Autowired
    WeatherRepo weatherRepo;

    public List<Object> suggestTracks(String cityName) {
        Weather weather = weatherRepo.getWeatherInfo(cityName);
        return findTracksByWeather(weather);
    }

    public List<Object> suggestTracks(float lat, float lon) {
        Weather weather = weatherRepo.getWeatherInfo(lat, lon);
        return findTracksByWeather(weather);
    }

    public List<Object> findTracksByWeather(Weather weather){
        Category category;
        if (weather.getMain().tempMax >= 30) {
            category = Category.PARTY;
        } else if (weather.getMain().tempMax >= 15 && weather.getMain().tempMax < 30) {
            category = Category.POP;
        } else if (weather.getMain().tempMax >= 10 && weather.getMain().tempMax < 15) {
            category = Category.ROCK;
        } else {
            category = Category.CLASSICAL;
        }

        return spotifyRepo.searchMusicTracks(category);
    }
}