package com.ifood.backend.advancedtest.service.spotify.interceptor;

import com.google.gson.Gson;
import feign.RequestTemplate;

public class CustomRequestInterceptor implements feign.RequestInterceptor {
    private static final Gson GSON = new Gson();

    @Override
    public void apply(RequestTemplate template) {
        String request;

        if (template.body() != null) {
            request = GSON.toJson(new String(template.body()));
            request
                    .replace("\\n", "")
                    .replace("\\\"", "\"");
        }
    }
}
