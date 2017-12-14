package com.ifood.backend.advancedtest.api.controller;

import com.ifood.backend.advancedtest.api.model.Coordinate;
import com.ifood.backend.advancedtest.api.model.Weather;
import com.ifood.backend.advancedtest.api.repo.WeatherRepo;
import com.ifood.backend.advancedtest.service.TrackSuggestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/playlist")
public class PlaylistController {

    @Autowired
    TrackSuggestionService trackSuggestionService;

    @RequestMapping(method = RequestMethod.GET, produces={"application/json"}, params={"name"})
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody List<Object> searchByName(@RequestParam(value = "name") String cityName) {
        List<Object> tracks = trackSuggestionService.suggestTracks(cityName);
        return tracks;
    }

    @RequestMapping(method = RequestMethod.GET, produces={" application/json"}, params={"lat","lon"})
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody List<Object> searchByCoord(@RequestParam(value = "lat") float lat,
                                              @RequestParam(value = "lon") float lon) {
        List<Object> tracks = trackSuggestionService.suggestTracks(lat, lon);
        return tracks;
    }
}
