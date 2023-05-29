package com.octo.ajava.infra.api_client;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.getRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.okJson;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.verify;
import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import com.github.tomakehurst.wiremock.matching.EqualToPattern;
import com.octo.ajava.fixture.TMDBJsonResponseFixture;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Value;

import java.util.Optional;

@WireMockTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TMDBHttpClientITest {

    private TMDBHttpClient tmdbHttpClient;

    @Value("${tmdb.token}") String jetonTmdb;

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
        verify(getRequestedFor(
                urlEqualTo("/movie/popular"))
                .withHeader("Authorization", equalTo("Bearer " + jetonTmdb))
        );
    }

    @Test
    public void chercherDesFilms() {
        // given
        stubFor(get("/search/movie?query=batman").willReturn(okJson(TMDBJsonResponseFixture.deuxFilms())));

        // when
        var result = tmdbHttpClient.chercherDesFilms("batman");

        // then
        verify(getRequestedFor(
                urlEqualTo("/search/movie?query=batman"))
                .withQueryParam("query", new EqualToPattern("batman"))
                .withHeader("Authorization", equalTo("Bearer " + jetonTmdb))
        );
    }

    @Test
    public void rechercherUnFilmNeFonctionnePasEtRenvoieUneErreur() {
        // given
        stubFor(get("/movie/1").willReturn(aResponse().withStatus(404).withBody("""
                {
                    "success": false,
                    "status_code": 34,
                    "status_message": "The resource you requested could not be found."
                }
                """)));

        // when
        var result = tmdbHttpClient.chercherUnFilmParId("1");

        // then
        verify(getRequestedFor(
                urlEqualTo("/movie/1"))
                .withHeader("Authorization", equalTo("Bearer " + jetonTmdb))
        );

        Assertions.assertEquals(Optional.empty(), result);
    }

    @Test
    public void rechercherUnFilmFonctionneEtRenvoieBienUnFilm() {
        // given
        stubFor(get("/movie/414906").willReturn(okJson(TMDBJsonResponseFixture.unFilm())));

        // when
        var result = tmdbHttpClient.chercherUnFilmParId("414906");

        // then
        verify(getRequestedFor(
                urlEqualTo("/movie/414906"))
                .withHeader("Authorization", equalTo("Bearer " + jetonTmdb))
        );

        Assertions.assertTrue(result.isPresent());
    }
}