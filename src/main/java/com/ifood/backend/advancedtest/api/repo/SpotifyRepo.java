package com.ifood.backend.advancedtest.api.repo;

import com.ifood.backend.advancedtest.api.model.Category;
import com.ifood.backend.advancedtest.api.model.Token;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SpotifyRepo {

    @Value("${services.spotify.token.uri}")
    private String tokenUri;

    @Value("${services.spotify.token.path}")
    private String tokenPath;

    @Value("${services.spotify.uri}")
    private String apiUri;

    @Value("${services.spotify.id}")
    private String clientId;

    @Value("${services.spotify.secret}")
    private String clientSecret;

    private RestTemplate restTemplate = new RestTemplate();
    private Token token;

    public List<Object> searchMusicTracks(Category category) {
        verifyAndRequestToken();
        return null;
    }

    private void verifyAndRequestToken() {

        String url = generateTokenRequestUrl();
        HttpHeaders headers = generateTokenRequestHeaders();

        Map<String, String> map = new HashMap<>();
        map.put("grant_type", "client_credentials");
        HttpEntity<Map<String, String>> req = new HttpEntity<>(map, headers);

        try {
            ResponseEntity<Token> responseEntity = restTemplate.postForEntity(url, req, Token.class);

            if (responseEntity.getStatusCode() == HttpStatus.OK) {
                token = responseEntity.getBody();
                return;
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    private String generateTokenRequestUrl() {
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString(tokenUri)
                .path(tokenPath);
        return builder.build().toString();
    }

    private HttpHeaders generateTokenRequestHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.ALL));
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        String clientCredentials = clientId + ":" + clientSecret;
        String authorizationValue = "Basic " + new String(Base64.encodeBase64(clientCredentials.getBytes()));

        httpHeaders.set("Authorization", authorizationValue);

        return httpHeaders;
    }
}
