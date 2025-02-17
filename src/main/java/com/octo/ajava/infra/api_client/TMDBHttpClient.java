package com.octo.ajava.infra.api_client;

import static java.util.Collections.emptyList;

import com.octo.ajava.infra.api_client.entities.PaginatedTMDBMovies;
import com.octo.ajava.infra.api_client.entities.TMDBMovie;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

@Service
@ConditionalOnProperty(name = "film.source", havingValue = "TMDB")
public class TMDBHttpClient {

  private static final String BEARER = "Bearer ";
  private final RestTemplate restTemplate;

  public TMDBHttpClient(
      @Value("${tmdb.baseUrl}") String urlTmdb, @Value("${tmdb.token}") String jetonTmdb) {
    restTemplate =
        new RestTemplateBuilder()
            .defaultHeader(HttpHeaders.AUTHORIZATION, BEARER + jetonTmdb)
            .readTimeout(Duration.of(2000, ChronoUnit.MILLIS))
            .connectTimeout(Duration.of(1000, ChronoUnit.MILLIS))
            .uriTemplateHandler(new DefaultUriBuilderFactory(urlTmdb))
            .build();
  }

  public List<TMDBMovie> recupererLesFilmsPopulaires() {
    ResponseEntity<PaginatedTMDBMovies> tmdbResponse =
        restTemplate.getForEntity("/movie/popular", PaginatedTMDBMovies.class);
    PaginatedTMDBMovies tmdbMovies = tmdbResponse.getBody();
    if (tmdbMovies == null) {
      return emptyList();
    }
    return tmdbMovies.getMovies();
  }
}
