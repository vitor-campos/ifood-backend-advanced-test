package com.ifood.backend.advancedtest.api.controller;

import com.ifood.backend.advancedtest.api.validator.InRange;
import com.ifood.backend.advancedtest.api.validator.Patterns;
import com.ifood.backend.advancedtest.domain.PlaylistResponse;
import com.ifood.backend.advancedtest.exception.NotFoundException;
import com.ifood.backend.advancedtest.service.TrackSuggestionService;
import com.ifood.backend.advancedtest.service.spotify.SpotifyErrorDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import javax.ws.rs.BadRequestException;

@RestController
@RequestMapping("/playlist")
@Validated
public class PlaylistController {

    private static final Logger logger =
            LoggerFactory.getLogger(SpotifyErrorDecoder.class);
    private static final String INVALID_VALUE_MESSAGE = "Invalid value";

    @Autowired
    TrackSuggestionService trackSuggestionService;

    @RequestMapping(method = RequestMethod.GET, produces={"application/json"}, params={"name"})
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody PlaylistResponse searchByName(@Valid
                                                           @Pattern(regexp = Patterns.CITY_NAME_PATTERN_REGEX, message = INVALID_VALUE_MESSAGE)
                                                           @RequestParam(value = "name") String cityName) {
        logger.debug("Request for city {}", cityName);
        PlaylistResponse tracks = trackSuggestionService.suggestTracks(cityName);
        return tracks;
    }

    @RequestMapping(method = RequestMethod.GET, produces={"application/json"}, params={"lat","lon"})
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody PlaylistResponse searchByCoord(@Valid @Pattern(regexp = Patterns.COORDINATE_PATTERN_REGEX, message = INVALID_VALUE_MESSAGE)
                                                            @InRange(min = -90, max = 90)
                                                            @RequestParam(value = "lat") float lat,
                                                        @Valid
                                                            @Pattern(regexp = Patterns.COORDINATE_PATTERN_REGEX, message = INVALID_VALUE_MESSAGE)
                                                             @InRange(min = -180, max = 180)
                                                            @RequestParam(value = "lon") float lon) throws NotFoundException, BadRequestException, NumberFormatException, TypeMismatchException {
        logger.debug("Request for coordinates {}, {}", lat, lon);
        PlaylistResponse tracks = trackSuggestionService.suggestTracks(lat, lon);
        return tracks;
    }
}
