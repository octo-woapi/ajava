package com.octo.ajava.infra.mapper;

import static com.octo.ajava.fixture.FilmTestBuilder.unFilm;
import static com.octo.ajava.fixture.TMDBMovieTestBuilder.unTMDBMovie;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.octo.ajava.domain.Film;
import com.octo.ajava.infra.api_client.entities.TMDBMovie;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TMDBFilmMapperUTest {

  @InjectMocks private TMDBFilmMapper tmdbFilmMapper;

  @Test
  void devrait_renvoyer_un_Film() {
    // Given
    TMDBMovie tmdbMovie = unTMDBMovie().build();
    Film filmAttendu = unFilm().avecTMDBMovie(tmdbMovie).build();

    // When
    Film filmRenvoye = tmdbFilmMapper.convertirEnFilm(tmdbMovie);

    // Then
    assertThat(filmRenvoye).isEqualTo(filmAttendu);
  }
}
