package com.octo.ajava.infra.api_client;

import static java.util.Optional.empty;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.web.reactive.function.client.WebClient.builder;

import com.octo.ajava.infra.api_client.entities.PaginatedTMDBMovies;
import com.octo.ajava.infra.api_client.entities.TMDBMovie;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@ConditionalOnProperty(name = "film.source", havingValue = "TMDB")
public class TMDBHttpClient {

  private static final String BEARER = "Bearer ";

  private WebClient webClient;

  public TMDBHttpClient(
      @Value("${tmdb.baseUrl}") String urlTmdb, @Value("${tmdb.token}") String jetonTmdb) {
    webClient = builder().baseUrl(urlTmdb).defaultHeader(AUTHORIZATION, BEARER + jetonTmdb).build();
  }

  public PaginatedTMDBMovies recupererLesFilmsPopulaires() {
    return webClient
        .get()
        .uri("/movie/popular")
        .retrieve()
        .bodyToMono(PaginatedTMDBMovies.class)
        .block();
  }

  public PaginatedTMDBMovies chercherDesFilms(String query) {
    return webClient
        .get()
        .uri("/search/movie?query=" + query)
        .retrieve()
        .bodyToMono(PaginatedTMDBMovies.class)
        .block();
  }

  public Optional<TMDBMovie> chercherUnFilmParId(int id) {
    try {
      return webClient
          .get()
          .uri("/movie/{id}", id)
          .retrieve()
          .bodyToMono(TMDBMovie.class)
          .blockOptional();
    } catch (Exception exception) {
      return empty();
    }
  }
}
