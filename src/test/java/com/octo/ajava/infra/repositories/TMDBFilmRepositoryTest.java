package com.octo.ajava.infra.repositories;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.octo.ajava.fixture.FilmFixture.deuxFilmsProvenantDeTMDB;
import static com.octo.ajava.fixture.TMDBMoviesFixture.deuxFilmsDeTMDBEnJSON;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import com.octo.ajava.infra.client.TMDBHttpClient;
import com.octo.ajava.infra.mapper.TMDBFilmMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@WireMockTest
class TMDBFilmRepositoryTest {

  private static TMDBFilmRepository tmdbFilmRepository;

  @BeforeAll()
  public static void prepare(WireMockRuntimeInfo wmRuntimeInfo) {
    var tmdbFilmMapper = new TMDBFilmMapper();
    var httpClient = new TMDBHttpClient("mon-token-tmdb-secret", wmRuntimeInfo.getHttpBaseUrl());
    tmdbFilmRepository = new TMDBFilmRepository(httpClient, tmdbFilmMapper);
  }

  @Nested
  @DisplayName("doit appeler l'api tmdb")
  public class doitAppelerApiTMDB {

    @Test
    @DisplayName("avec une url en /movie/popular ")
    public void movie_popular_avec_la_bonne_url() throws Exception {
      // given
      stubFor(get("/movie/popular").willReturn(ok()));
      // when
      tmdbFilmRepository.recupererLesFilms();
      // then
      verify(getRequestedFor(urlEqualTo("/movie/popular")));
    }

    @Test
    @DisplayName("avec le header authorization et le token")
    public void movie_popular_avec_le_header_authorization() throws Exception {
      // given
      stubFor(get("/movie/popular").willReturn(ok()));
      // when
      tmdbFilmRepository.recupererLesFilms();
      // then
      verify(
          getRequestedFor(urlEqualTo("/movie/popular"))
              .withHeader("Authorization", equalTo("Bearer mon-token-tmdb-secret")));
    }
  }

  @Test
  @DisplayName("doit retourner une liste de films depuis l'API TMDB")
  public void doitRetournerUneListeDeFilmsDepuisApiTMDB() throws Exception {
    // given
    var expectedFilms = deuxFilmsProvenantDeTMDB();
    stubFor(get("/movie/popular").willReturn(okJson(deuxFilmsDeTMDBEnJSON())));
    // when
    var films = tmdbFilmRepository.recupererLesFilms();
    // then
    assertThat(films).usingRecursiveComparison().isEqualTo(expectedFilms);
  }

  @Test
  @DisplayName("rechercherDesFilms retourne une liste de films")
  public void rechercherDesFilms_retourne_une_liste_de_films() throws Exception {
    // given
    var expectedFilms = deuxFilmsProvenantDeTMDB();
    stubFor(get("/search/movie?query=batman").willReturn(okJson(deuxFilmsDeTMDBEnJSON())));
    // when
    var films = tmdbFilmRepository.chercherDesFilms("batman");
    // then
    assertThat(films).usingRecursiveComparison().isEqualTo(expectedFilms);
  }
}
