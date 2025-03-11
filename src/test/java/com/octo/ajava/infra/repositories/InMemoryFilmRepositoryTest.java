package com.octo.ajava.infra.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import com.octo.ajava.domain.Film;
import java.util.List;
import org.junit.jupiter.api.Test;

class InMemoryFilmRepositoryTest {

  @Test
  void doit_retourner_une_liste_de_film() throws Exception {
    InMemoryFilmRepository inMemoryFilmRepository = new InMemoryFilmRepository();

    List<Film> result = inMemoryFilmRepository.recupererLesFilms();

    assertThat(result).hasSize(23);
  }
}
