package com.octo.ajava.infra.api_client;

import com.octo.ajava.infra.api_client.entities.PaginatedTMDBMovies;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@ConditionalOnProperty(name = "film.source", havingValue = "TMDB")
public class TMDBHttpClient {

  private static final String BEARER = "Bearer ";

  private WebClient webClient;

  public TMDBHttpClient(
      @Value("${tmdb.baseUrl}") String urlTmdb, @Value("${tmdb.token}") String jetonTmdb) {
    webClient =
        WebClient.builder()
            .baseUrl(urlTmdb)
            .defaultHeader(HttpHeaders.AUTHORIZATION, BEARER + jetonTmdb)
            .build();
  }

  public PaginatedTMDBMovies recupererLesFilmsPopulaires() {
    return webClient
        .get()
        .uri("/movie/popular")
        .retrieve()
        .bodyToMono(PaginatedTMDBMovies.class)
        .block();
  }
}
