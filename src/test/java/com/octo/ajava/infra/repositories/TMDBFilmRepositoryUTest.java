package com.octo.ajava.infra.repositories;

import static com.octo.ajava.fixture.FilmTestBuilder.unFilm;
import static com.octo.ajava.fixture.TMDBMovieTestBuilder.unTMDBMovie;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import com.octo.ajava.domain.Film;
import com.octo.ajava.infra.api_client.TMDBHttpClient;
import com.octo.ajava.infra.api_client.entities.TMDBMovie;
import com.octo.ajava.infra.mapper.TMDBFilmMapper;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TMDBFilmRepositoryUTest {

  @InjectMocks TMDBFilmRepository tmdbFilmRepository;
  @Mock private TMDBHttpClient tmdbHttpClient;
  @Mock private TMDBFilmMapper tmdbFilmMapper;

  @Test
  void recupererLesFilms_devrait_renvoyer_une_liste_de_films_TMDB() {
    // Given
    TMDBMovie tmdbMovie = unTMDBMovie().build();
    List<TMDBMovie> listDeFilmsTMDB = List.of(tmdbMovie);
    List<Film> listDeFilmsAttendus = List.of(unFilm().avecTMDBMovie(tmdbMovie).build());
    given(tmdbHttpClient.recupererLesFilmsPopulaires()).willReturn(listDeFilmsTMDB);
    given(tmdbFilmMapper.convertirEnFilms(listDeFilmsTMDB)).willReturn(listDeFilmsAttendus);

    // When
    List<Film> filmsRecuperes = tmdbFilmRepository.recupererLesFilms();

    // Then
    assertThat(filmsRecuperes).isNotEmpty();
    assertThat(filmsRecuperes).isEqualTo(listDeFilmsAttendus);
  }
}
