package com.octo.ajava.infra.api_client;

import com.octo.ajava.infra.api_client.entities.PaginatedTMDBMovies;
import com.octo.ajava.infra.api_client.entities.TMDBMovie;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
@ConditionalOnProperty(name = "film.source", havingValue = "TMDB")
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

  public Optional<TMDBMovie> chercherUnFilmParId(String id) {
    try {
      return webClient.get()
              .uri("/movie/" + id)
              .retrieve()
              .bodyToMono(TMDBMovie.class)
              .blockOptional();
    } catch (Exception e) {
      return Optional.empty();
    }
  }
}
