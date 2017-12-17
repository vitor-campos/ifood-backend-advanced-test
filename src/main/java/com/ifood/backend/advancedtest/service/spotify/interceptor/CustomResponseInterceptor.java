package com.ifood.backend.advancedtest.service.spotify.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.jackson.JacksonDecoder;

import java.io.IOException;
import java.lang.reflect.Type;

public class CustomResponseInterceptor extends JacksonDecoder {

    public CustomResponseInterceptor() {
        super();
    }

    public CustomResponseInterceptor(ObjectMapper mapper) {
        super(mapper);
    }

    @Override
    public Object decode(Response response, Type type) throws IOException {
        return super.decode(response, type);
    }
}
