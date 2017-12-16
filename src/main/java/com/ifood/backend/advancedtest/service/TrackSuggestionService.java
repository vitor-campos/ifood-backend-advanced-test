package com.ifood.backend.advancedtest.service;

import com.ifood.backend.advancedtest.domain.Category;
import com.ifood.backend.advancedtest.domain.PlaylistResponse;
import com.ifood.backend.advancedtest.domain.Weather;
import com.ifood.backend.advancedtest.service.openweather.OpenWeatherApiClient;
import com.ifood.backend.advancedtest.service.spotify.client.SpotifyPlaylistApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TrackSuggestionService {

    @Autowired
    SpotifyPlaylistApiClient spotifyPlaylistApiClient;

    @Autowired
    OpenWeatherApiClient openWeatherApiClient;

    public PlaylistResponse suggestTracks(String cityName) {
        Weather weather = openWeatherApiClient.getWeatherInfo(cityName);
        return findTracksByWeather(weather);
    }

    public PlaylistResponse suggestTracks(float lat, float lon) {
        Weather weather = openWeatherApiClient.getWeatherInfo(lat, lon);
        return findTracksByWeather(weather);
    }

    public PlaylistResponse findTracksByWeather(Weather weather){
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

        return spotifyPlaylistApiClient.getPlayList("spotify", category.getPlaylistId());
    }
}