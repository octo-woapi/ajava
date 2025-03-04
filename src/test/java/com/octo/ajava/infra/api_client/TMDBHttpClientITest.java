package com.octo.ajava.infra.api_client;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.getRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.okJson;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.verify;
import static com.octo.ajava.fixtures.TMDBJsonResponseTestFixture.deuxFilms;
import static com.octo.ajava.fixtures.TMDBJsonResponseTestFixture.unFilm;
import static org.apache.http.HttpStatus.SC_NOT_FOUND;
import static org.assertj.core.api.Assertions.assertThat;

import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import com.octo.ajava.infra.api_client.entities.TMDBMovie;
import java.util.Optional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
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
            .withQueryParam("query", equalTo("batman"))
            .withHeader("Authorization", equalTo("Bearer " + jetonTmdb)));
  }

  @DisplayName("#chercherUnFilmParId")
  @Nested
  class ChercherUnFilmParIdTest {

    @DisplayName("devrait renvoyer une réponse vide lorsque le Film n'existe pas")
    @Test
    void filmNonExistant() {
      // Given
      stubFor(
          get("/movie/1")
              .willReturn(
                  aResponse()
                      .withStatus(SC_NOT_FOUND)
                      .withBody(
                          """
                        {
                            "success": false,
                            "status_code": 34,
                            "status_message": "The resource you requested could not be found."
                        }
                        """)));

      // When
      Optional<TMDBMovie> filmTrouve = tmdbHttpClient.chercherUnFilmParId(1);

      // Then
      verify(
          getRequestedFor(urlEqualTo("/movie/1"))
              .withHeader("Authorization", equalTo("Bearer " + jetonTmdb)));

      assertThat(filmTrouve).isEmpty();
    }

    @DisplayName("devrait renvoyer le Film recherché par id")
    @Test
    public void filmExistant() {
      // Given
      stubFor(get("/movie/414906").willReturn(okJson(unFilm())));

      // When
      Optional<TMDBMovie> filmTrouve = tmdbHttpClient.chercherUnFilmParId(414906);

      // Then
      verify(
          getRequestedFor(urlEqualTo("/movie/414906"))
              .withHeader("Authorization", equalTo("Bearer " + jetonTmdb)));

      assertThat(filmTrouve).isNotEmpty();
    }
  }
}
