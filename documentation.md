# High Level Documentation

## Technologies, Frameworks and Libraries
 - Java 8
 - Spring Boot
 - Spring Security
 - Spring Rest
 - Spring Validation
 - Spring Cache
 - Netflix Feign
 - Swagger and Swagger UI
 - PowerMock and Mockito
 - Logback
 - Travis CI
 - SonarQube Cloud
 - Docker
 - Heroku

## Considerations:
### OpenWeather service
  To use this service it's only necessary to have an application id, but the number of requests are limitated to 60 per minute.
  
  It's doesn't refresh the application id.
  
  Important: When use a city coordinates, the response data isn't the same of the request sending the city name

### Spotify Service
  Spotify provides an authentication process based in temporary token.
  
  There's support to pagination in playlist request, but it's not being used in this microservice

## Architecture
 ### REST Api, with a controller mapping two endpoints:
    /playlist?city=
    /playlist?lat=&lon=

   __Package: com.ifood.backend.advancedtest.api__

 ### OpenWeather service consumer
   - Api Client: Performs api calls to OpenWeather by city name and coordinates
   - Request Interceptor: Adds APPID query param to request
   - Error Decoder: Handles errors from external api calls and customize the response
    
   __Package: com.ifood.backend.advancedtest.service.openweather__

 ### Spotify service consumer
   - Auth Api Client: Performs authentication api call
   - Playlist Api Client: Performs playlist api call
   - Request Interceptor: Verifies if token has expired and request a new one if needed
   - Request Authentication Interceptor: Adds authorization value to request header
   - Api Authentication Service: Handles cache manager to store auth token
   - Error Decoder: Verifies if the api call response is UNAUTHORIZED and remove the token from cache forcing to be requested a new one

   __Package: com.ifood.backend.advancedtest.service.spotify__

 ### TrackSuggestionService
   This class is invoked by REST API Controller and performs the request to OpenWeather API and Spotify API
   
   __Class com.ifood.backend.advancedtest.service.TrackSuggestionService__

 ### Global Error Handling
   This project provides a centralized error handling based in ControllerAdvice. It's used to catch some kinds of error and customize the response.
   
   __Class com.ifood.backend.advancedtest.exception.GlobalControllerExceptionHandler__
## Possible Improvements
  - Increase unit test coverage
  - Implement integrated tests
  - Add Hystrix circuit breaker to improve fault tolerance
  - Implement OAuth to improve security
  - Improve cache storage (using Redis)


## Setup and Usage:
### Configuration
   __Update configurations in src/main/resources/application.yml__
  
  ```javascript
  security:
    username: user //Specify
    password: passwd123@ //Specify
  
  services:
    weather:
      uri: http://api.openweathermap.org/data/2.5/weather
      id: 398b8ddf855a1fb8322da99648fb4ebc //Replace with your OpenWeather AppID
      timeout: 5000
    spotify:
      token:
        uri: https://accounts.spotify.com
        path: /api/token
        id: c724fa1199704f74b0f2b46b62408e2e //Replace with your Spotify ClientId
        secret: 17d962e5a2304d8383cfcbb84cc9e135 //Replace with your OpenWeather ClientSecret
      uri: https://api.spotify.com
      timeout: 5000
  ```
  __Build Locally__
  
  1. Build docker image
  ```bash
    ./gradlew build docker
  ```
  
  2. Start docker container
  ```bash
      docker run -p 8080:8080 -t com.ifood.backend/ifood-backend-advanced-test
  ```
  
  3. Test Rest Api
  ```http
  http://localhost:8080/playlist?city=Salvador
  http://localhost:8080/playlist?lat=-30.2&lon=-5.3
  ```
  
  4. Swagger docs
  ```http
  http://localhost:8080/swagger-ui.html
   ```
   
 __Deployed Application__
  1. Test Rest Api
   ```http
   https://ifood-backend-test-vc.herokuapp.com/playlist?city=Salvador
   https://ifood-backend-test-vc.herokuapp.com/playlist?lat=-30.2&lon=-5.3
   ```
   
   2. Swagger docs
   ```http
   https://ifood-backend-test-vc.herokuapp.com/swagger-ui.html
    ```
