# iFood Backend Advanced Test

Create a micro-service able to accept RESTful requests receiving as parameter either city name or lat long coordinates and returns a playlist (only track names is fine) suggestion according to the current temperature.

## Business rules

* If temperature (celcius) is above 30 degrees, suggest tracks for party
* In case temperature is above 15 and below 30 degrees, suggest pop music tracks
* If it's a bit chilly (between 10 and 14 degrees), suggest rock music tracks
* Otherwise, if it's freezing outside, suggests classical music tracks 

## Hints

You can make usage of OpenWeatherMaps API (https://openweathermap.org) to fetch temperature data and Spotify (https://developer.spotify.com) to suggest the tracks as part of the playlist.

## Non functional requirements

As this service will be a worldwide success,it must be prepared to be fault tolerant, responsive and resilient.

Use whatever language, tools and frameworks you feel comfortable to.

Briefly elaborate on your solution, architecture details, choice of patterns and frameworks.

Also, make it easy to deploy/run you service locally (consider using some container/vm solution for this).

Fork this repository and submit your code.


## Setup and Usage:
### Configuration
  1. Update configurations in src/main/resources/application.yml
  
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
  
  2. Build docker image
  ```bash
    ./gradlew build docker
  ```
  
  3. Start docker container
  ```bash
      ./docker run -p 8080:8080 -t com.ifood.backend/ifood-backend-advanced-test
  ```
  
  4. Test Rest Api
  ```http
  http://localhost:8080/playlist?city=Salvador
  http://localhost:8080/playlist?lat=-30.2&lon=-5.3
  ```
  
  5. Swagger docs
  ```http
  http://localhost:8080/swagger-ui.html
   ```
   
  [Link to Documentation](./documentation.md)
  
  [Link to Acceptance Criteria](./acceptancecriteria.md)