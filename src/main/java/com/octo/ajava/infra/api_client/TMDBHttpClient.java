package com.octo.ajava.infra.api_client;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

import com.octo.ajava.infra.api_client.entities.PaginatedTMDBMovies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;

@Service
public class TMDBHttpClient {

  private static final String BEARER = "Bearer ";

  private WebClient webClient;

  public TMDBHttpClient(
          @Value("${tmdb.baseUrl}") String urlTmdb,
          @Value("${tmdb.token}") String jetonTmdb
  ) {
    webClient = WebClient.builder()
            .baseUrl(urlTmdb)
            .defaultHeader(HttpHeaders.AUTHORIZATION, BEARER + jetonTmdb)
            .build();
  }

  public PaginatedTMDBMovies recupererLesFilmsPopulaires() {
    return webClient.get()
            .uri("/movie/popular")
            .retrieve()
            .bodyToMono(PaginatedTMDBMovies.class)
            .block();
  }

  public PaginatedTMDBMovies chercherDesFilms(String query) {
    return webClient.get()
            .uri("/search/movie?query=" + query)
            .retrieve()
            .bodyToMono(PaginatedTMDBMovies.class)
            .block();
  }
}
