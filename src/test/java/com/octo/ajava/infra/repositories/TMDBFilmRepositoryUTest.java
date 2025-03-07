package com.octo.ajava.infra.repositories;

import static com.octo.ajava.fixtures.FilmTestFixture.deuxFilmsPopulaires;
import static com.octo.ajava.fixtures.FilmTestFixture.deuxFilmsRecherches;
import static com.octo.ajava.fixtures.TMDBMovieTestFixture.deuxFilmsPopulairesVenantDeTMTB;
import static com.octo.ajava.fixtures.TMDBMovieTestFixture.deuxFilmsRecherchesVenantDeTMTB;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import com.octo.ajava.domain.Film;
import com.octo.ajava.infra.api_client.TMDBHttpClient;
import com.octo.ajava.infra.api_client.entities.PaginatedTMDBMovies;
import com.octo.ajava.infra.mapper.TMDBFilmMapper;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TMDBFilmRepositoryTest {

  @Mock private TMDBHttpClient tmdbHttpClient;
  private TMDBFilmRepository tmdbFilmRepository;

  @BeforeEach
  public void setUp() {
    tmdbFilmRepository = new TMDBFilmRepository(tmdbHttpClient, new TMDBFilmMapper());
  }

  @Test
  void recupererLesFilms_retourne_une_liste_de_films_TMDB() {
    // Given
    given(tmdbHttpClient.recupererLesFilmsPopulaires())
        .willReturn(new PaginatedTMDBMovies(1, deuxFilmsPopulairesVenantDeTMTB(), 2, 1));

    // When
    List<Film> filmsTrouves = tmdbFilmRepository.recupererLesFilms();

    // Then
    assertThat(filmsTrouves).isEqualTo(deuxFilmsPopulaires());
  }

  @Test
  void rechercherDesFilms_retourne_une_liste_de_films() {
    // Given
    given(tmdbHttpClient.chercherDesFilms("batman"))
        .willReturn(new PaginatedTMDBMovies(1, deuxFilmsRecherchesVenantDeTMTB(), 2, 1));

    // When
    List<Film> filmsTrouves = tmdbFilmRepository.chercherDesFilms("batman");

    // Then
    assertThat(filmsTrouves).isEqualTo(deuxFilmsRecherches());
  }
}
