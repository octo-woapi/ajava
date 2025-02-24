package com.octo.ajava.infra.api_client;

import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.getRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.okJson;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.verify;
import static com.octo.ajava.fixture.TMDBJsonResponseFixture.deuxFilms;

import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import com.github.tomakehurst.wiremock.matching.EqualToPattern;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Value;

@WireMockTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TMDBHttpClientTest {

  private TMDBHttpClient tmdbHttpClient;

  @Value("${tmdb.token}")
  private String jetonTmdb;

  @BeforeAll()
  void prepare(WireMockRuntimeInfo wmRuntimeInfo) {
    tmdbHttpClient = new TMDBHttpClient(wmRuntimeInfo.getHttpBaseUrl(), jetonTmdb);
  }

  @Test
  void recupererLesFilmsPopulaires() {
    // Given
    stubFor(get("/movie/popular").willReturn(okJson(deuxFilms())));

    // When
    tmdbHttpClient.recupererLesFilmsPopulaires();

    // Then
    verify(
        getRequestedFor(urlEqualTo("/movie/popular"))
            .withHeader("Authorization", equalTo("Bearer " + jetonTmdb)));
  }

  @Test
  void chercherDesFilms() {
    // Given
    stubFor(get("/search/movie?query=batman").willReturn(okJson(deuxFilms())));

    // When
    tmdbHttpClient.chercherDesFilms("batman");

    // Then
    verify(
        getRequestedFor(urlEqualTo("/search/movie?query=batman"))
            .withQueryParam("query", new EqualToPattern("batman"))
            .withHeader("Authorization", equalTo("Bearer " + jetonTmdb)));
  }
}
