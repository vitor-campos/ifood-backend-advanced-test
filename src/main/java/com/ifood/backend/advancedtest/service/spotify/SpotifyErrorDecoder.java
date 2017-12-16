package com.ifood.backend.advancedtest.service.spotify;

import com.google.gson.Gson;
import com.ifood.backend.advancedtest.exception.ExpiredSessionException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.beans.factory.annotation.Autowired;

public class SpotifyErrorDecoder implements ErrorDecoder {

    private static final Gson GSON = new Gson();

    private static final int BAD_REQUEST = 400;
    private static final int UNAUTHORIZED = 401;
    private static final int FORBIDDEN = 403;
    private static final int NOT_FOUND = 404;
    private static final int CONFLICT = 409;

    @Autowired
    SpotifyApiAuthenticationService spotifyApiAuthenticationService;

    @Override
    public Exception decode(String methodKey, Response response) {
        if (response.status() == UNAUTHORIZED) {
            //Caso o token tenha expirado remove o token do cache e lança uma exceção para forçar
            //o Feign a chamar novamente a API de auth
            spotifyApiAuthenticationService.removeTokenFromCache();
            throw new ExpiredSessionException("Session Expired");
        }

        return new ErrorDecoder.Default().decode(methodKey, response);
    }
}
