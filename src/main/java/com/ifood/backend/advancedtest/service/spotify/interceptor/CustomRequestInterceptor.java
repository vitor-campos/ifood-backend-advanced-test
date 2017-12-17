package com.ifood.backend.advancedtest.service.spotify.interceptor;

import com.google.gson.Gson;
import com.ifood.backend.advancedtest.service.spotify.SpotifyErrorDecoder;
import feign.RequestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomRequestInterceptor implements feign.RequestInterceptor {
    private static final Gson GSON = new Gson();

    private static final Logger logger =
            LoggerFactory.getLogger(SpotifyErrorDecoder.class);

    @Override
    public void apply(RequestTemplate template) {
        String request;

        if (template.body() != null) {
            request = GSON.toJson(new String(template.body()))
                    .replace("\\n", "")
                    .replace("\\\"", "\"");

            logger.debug("Sending request to Spotify {}", request);
        }
    }
}
