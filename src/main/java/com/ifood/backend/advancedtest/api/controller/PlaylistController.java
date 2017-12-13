package com.ifood.backend.advancedtest.api.controller;

import com.ifood.backend.advancedtest.api.model.Coordinate;
import com.ifood.backend.advancedtest.api.model.Weather;
import com.ifood.backend.advancedtest.api.repo.WeatherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/weather")
public class PlaylistController {

    @Autowired
    WeatherRepo weatherRepo;

    @RequestMapping(method = RequestMethod.GET, produces={"application/json"}, params={"name"})
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Weather searchByName(@RequestParam(value = "name") String cityName) {
        Weather weather = weatherRepo.getWeatherInfo(cityName);
        return weather;
    }

    @RequestMapping(method = RequestMethod.GET, produces={"application/json"}, params={"lat","lon"})
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Weather searchByCoord(@RequestParam(value = "lat") float lat,
                                              @RequestParam(value = "lon") float lon) {
        Weather weather = weatherRepo.getWeatherInfo(lat, lon);
        return weather;
    }
}
