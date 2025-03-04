package com.octo.ajava.infra.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import com.octo.ajava.domain.Film;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class InMemoryFilmRepositoryTest {

  @DisplayName("devrait renvoyer la liste de tous les films")
  @Test
  public void recupereLesFilms() {
    // Given
    InMemoryFilmRepository inMemoryFilmRepository = new InMemoryFilmRepository();

    // When
    List<Film> films = inMemoryFilmRepository.recupererLesFilms();

    // Then
    assertThat(films).hasSize(23);
  }
}
