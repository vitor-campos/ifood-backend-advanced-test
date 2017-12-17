package com.ifood.backend.advancedtest.service.spotify.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.jackson.JacksonDecoder;

public class CustomResponseInterceptor extends JacksonDecoder {

    public CustomResponseInterceptor() {
        super();
    }

    public CustomResponseInterceptor(ObjectMapper mapper) {
        super(mapper);
    }

}
