package com.ifood.backend.advancedtest.api.repo;

import com.ifood.backend.advancedtest.api.model.Weather;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "services.weather", url = "${services.weather.uri}")
public interface WeatherRepo {

    @RequestMapping(method = RequestMethod.GET, value = "${services.weather.paths.city}", consumes = "application/json")
    Weather getWeatherInfo(@PathVariable("name") String cityName);

    @RequestMapping(method = RequestMethod.GET, value = "${services.weather.paths.coord}", consumes = "application/json")
    Weather getWeatherInfo(@PathVariable("lat") float lat, @PathVariable("lon") float lon);

}
