package com.ifood.backend.advancedtest.api.controller;

import com.ifood.backend.advancedtest.domain.PlaylistResponse;
import com.ifood.backend.advancedtest.service.TrackSuggestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/playlist")
public class PlaylistController {

    @Autowired
    TrackSuggestionService trackSuggestionService;

    @RequestMapping(method = RequestMethod.GET, produces={"application/json"}, params={"name"})
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody PlaylistResponse searchByName(@RequestParam(value = "name") String cityName) {
        PlaylistResponse tracks = trackSuggestionService.suggestTracks(cityName);
        return tracks;
    }

    @RequestMapping(method = RequestMethod.GET, produces={"application/json"}, params={"lat","lon"})
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody PlaylistResponse searchByCoord(@RequestParam(value = "lat") float lat,
                                              @RequestParam(value = "lon") float lon) {
        PlaylistResponse tracks = trackSuggestionService.suggestTracks(lat, lon);
        return tracks;
    }
}
