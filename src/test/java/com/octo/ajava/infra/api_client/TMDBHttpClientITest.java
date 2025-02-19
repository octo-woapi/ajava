package com.octo.ajava.infra.api_client;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import com.github.tomakehurst.wiremock.matching.EqualToPattern;
import com.octo.ajava.fixture.TMDBJsonResponseFixture;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Value;

@WireMockTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TMDBHttpClientITest {

  private TMDBHttpClient tmdbHttpClient;

  @Value("${tmdb.token}")
  String jetonTmdb;

  @BeforeAll()
  public void prepare(WireMockRuntimeInfo wmRuntimeInfo) {
    tmdbHttpClient = new TMDBHttpClient(wmRuntimeInfo.getHttpBaseUrl(), jetonTmdb);
  }

  @Test
  public void recupererLesFilmsPopulaires() {
    // given
    stubFor(get("/movie/popular").willReturn(okJson(TMDBJsonResponseFixture.deuxFilms())));

    // when
    var result = tmdbHttpClient.recupererLesFilmsPopulaires();

    // then
    verify(
        getRequestedFor(urlEqualTo("/movie/popular"))
            .withHeader("Authorization", equalTo("Bearer " + jetonTmdb)));
  }

  @Test
  public void chercherDesFilms() {
    // given
    stubFor(
        get("/search/movie?query=batman").willReturn(okJson(TMDBJsonResponseFixture.deuxFilms())));

    // when
    var result = tmdbHttpClient.chercherDesFilms("batman");

    // then
    verify(
        getRequestedFor(urlEqualTo("/search/movie?query=batman"))
            .withQueryParam("query", new EqualToPattern("batman"))
            .withHeader("Authorization", equalTo("Bearer " + jetonTmdb)));
  }
}
