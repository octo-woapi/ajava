package com.octo.ajava.infra.client;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

@Service
@ConditionalOnProperty(name = "film.source", havingValue = "TMDB")
public class TMDBHttpClient {

  private final RestTemplate restTemplate;

  private static final String BEARER = "Bearer ";

  public TMDBHttpClient(
      @Value("${tmdb.token}") final String accessToken,
      @Value("${tmdb.baseUrl}") final String baseUrl) {
    /**
     * Construction d'un client HTTP spécifique à TMDB : - Ajout de l'authentification par token de
     * TMDB - Ajout d'une configuration de timeout (pour ne pas surcharger le connexion pool) -
     * Ajout de l'url par défaut de TMDB
     */
    this.restTemplate =
        new RestTemplateBuilder()
            .defaultHeader(HttpHeaders.AUTHORIZATION, BEARER + accessToken)
            .setReadTimeout(Duration.of(2000, ChronoUnit.MILLIS))
            .setConnectTimeout(Duration.of(1000, ChronoUnit.MILLIS))
            .uriTemplateHandler(new DefaultUriBuilderFactory(baseUrl))
            .build();
  }

  public RestTemplate get() {
    return this.restTemplate;
  }
}
