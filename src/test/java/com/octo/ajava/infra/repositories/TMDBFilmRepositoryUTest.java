package com.octo.ajava.infra.repositories;

import static org.mockito.BDDMockito.given;

import com.octo.ajava.fixture.FilmFixture;
import com.octo.ajava.fixture.TMDBMovieFixture;
import com.octo.ajava.infra.api_client.TMDBHttpClient;
import com.octo.ajava.infra.api_client.entities.PaginatedTMDBMovies;
import com.octo.ajava.infra.mapper.TMDBFilmMapper;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TMDBFilmRepositoryUTest {

  private TMDBFilmRepository tmdbFilmRepository;

  @Mock private TMDBHttpClient tmdbHttpClient;

  private TMDBFilmMapper tmdbFilmMapper = new TMDBFilmMapper();

  @BeforeEach
  public void setUp() {
    tmdbFilmRepository = new TMDBFilmRepository(tmdbHttpClient, tmdbFilmMapper);
  }

  @Test
  void recupererLesFilms_retourne_une_liste_de_films_TMDB() {
    // Given
    given(tmdbHttpClient.recupererLesFilmsPopulaires())
        .willReturn(
            new PaginatedTMDBMovies(1, TMDBMovieFixture.deuxFilmsPopulairesVenantDeTMTB(), 2, 1));

    // When
    var result = tmdbFilmRepository.recupererLesFilms();

    // Then
    Assertions.assertEquals(FilmFixture.deuxFilmsPopulaires(), result);
  }

  @Test
  void rechercherDesFilms_retourne_une_liste_de_films() {
    // Given
    given(tmdbHttpClient.chercherDesFilms("batman"))
        .willReturn(
            new PaginatedTMDBMovies(1, TMDBMovieFixture.deuxFilmsRecherchesVenantDeTMTB(), 2, 1));

    // When
    var result = tmdbFilmRepository.chercherDesFilms("batman");

    // Then
    Assertions.assertEquals(FilmFixture.deuxFilmsRecherches(), result);
  }

  @Test
  void chercherUnFilmParId_retourne_un_film_quand_il_est_present_dans_la_reponse() {
    // Given
    given(tmdbHttpClient.chercherUnFilmParId("414906"))
        .willReturn(
            Optional.of(
                // film renvoyé par tmdb
                ));

    // When
    var result = tmdbFilmRepository.chercherUnFilmParId("414906");

    // Then
    Assertions.assertEquals(expected, result);
  }

  @Test
  void chercherUnFilmParId_retourne_empty_quand_il_n_est_present_pas_dans_la_reponse() {
    // Given
    given(tmdbHttpClient.chercherUnFilmParId("1")).willReturn(Optional.empty());

    // When
    var result = tmdbFilmRepository.chercherUnFilmParId("1");

    // Then
    Assertions.assertEquals(expected, result);
  }
}
