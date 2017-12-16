package com.ifood.backend.advancedtest.api.controller;

import com.ifood.backend.advancedtest.domain.PlaylistResponse;
import com.ifood.backend.advancedtest.exception.WeatherNotFoundException;
import com.ifood.backend.advancedtest.service.TrackSuggestionService;
import com.ifood.backend.advancedtest.service.spotify.SpotifyErrorDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/playlist")
public class PlaylistController {

    private static final Logger logger =
            LoggerFactory.getLogger(SpotifyErrorDecoder.class);

    @Autowired
    TrackSuggestionService trackSuggestionService;

    @RequestMapping(method = RequestMethod.GET, produces={"application/json"}, params={"name"})
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler({ WeatherNotFoundException.class})
    public @ResponseBody PlaylistResponse searchByName(@RequestParam(value = "name") String cityName) throws WeatherNotFoundException {
        logger.debug("Request for city {}", cityName);
        PlaylistResponse tracks = trackSuggestionService.suggestTracks(cityName);
        return tracks;
    }

    @RequestMapping(method = RequestMethod.GET, produces={"application/json"}, params={"lat","lon"})
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody PlaylistResponse searchByCoord(@RequestParam(value = "lat") float lat,
                                              @RequestParam(value = "lon") float lon) throws WeatherNotFoundException {
        logger.debug("Request for coordinates {}, {}", lat, lon);
        PlaylistResponse tracks = trackSuggestionService.suggestTracks(lat, lon);
        return tracks;
    }
}
