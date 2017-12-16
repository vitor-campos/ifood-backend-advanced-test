package com.ifood.backend.advancedtest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class WeatherNotFoundException extends RuntimeException {

    public WeatherNotFoundException(String msg) {
        super(msg);
    }

    public WeatherNotFoundException(String msg, Exception e) {
        super(msg, e);
    }
}
