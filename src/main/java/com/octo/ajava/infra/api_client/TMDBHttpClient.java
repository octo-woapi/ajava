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
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;

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
}
