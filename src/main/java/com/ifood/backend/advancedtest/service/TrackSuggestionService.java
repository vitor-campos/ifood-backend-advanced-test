package com.ifood.backend.advancedtest.service;

import com.ifood.backend.advancedtest.domain.Category;
import com.ifood.backend.advancedtest.domain.PlaylistResponse;
import com.ifood.backend.advancedtest.domain.Weather;
import com.ifood.backend.advancedtest.service.openweather.OpenWeatherApiClient;
import com.ifood.backend.advancedtest.service.spotify.client.SpotifyPlaylistApiClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TrackSuggestionService {

    private static final Logger logger =
            LoggerFactory.getLogger(TrackSuggestionService.class);

    @Autowired
    SpotifyPlaylistApiClient spotifyPlaylistApiClient;

    @Autowired
    OpenWeatherApiClient openWeatherApiClient;

    public PlaylistResponse suggestTracks(String cityName) {
        Weather weather = openWeatherApiClient.getWeatherInfo(cityName);
        logger.debug("Weather found for city {}: {}", cityName, weather);
        return findTracksByWeather(weather);

    }

    public PlaylistResponse suggestTracks(float lat, float lon) {
        Weather weather = openWeatherApiClient.getWeatherInfo(lat, lon);
        logger.debug("Weather found for coordinates  {}, {}: {}", lat, lon, weather);

        return findTracksByWeather(weather);
    }

    public PlaylistResponse findTracksByWeather(Weather weather){
        Category category;
        float temp = weather.getMain().getTemp();
        logger.debug("Selecting playlist according to temperature: {}", temp);
        if (temp >= 30) {
            category = Category.PARTY;
        } else if (temp >= 15 && temp < 30) {
            category = Category.POP;
        } else if (temp >= 10 && temp < 15) {
            category = Category.ROCK;
        } else {
            category = Category.CLASSICAL;
        }

        logger.debug("Selected playlist {} to temperature: {}", category, temp);

        return spotifyPlaylistApiClient.getPlayList("spotify", category.getPlaylistId());
    }
}