package com.octo.ajava.infra.repositories;

import static org.mockito.BDDMockito.given;

import com.octo.ajava.fixture.FilmFixture;
import com.octo.ajava.fixture.TMDBMovieFixture;
import com.octo.ajava.infra.api_client.TMDBHttpClient;
import com.octo.ajava.infra.api_client.entities.PaginatedTMDBMovies;
import com.octo.ajava.infra.mapper.TMDBFilmMapper;
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
}
