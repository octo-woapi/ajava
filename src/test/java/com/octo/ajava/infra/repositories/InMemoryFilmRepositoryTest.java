package com.octo.ajava.infra.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class InMemoryFilmRepositoryTest {

  @Test
  public void doit_retourner_une_liste_de_film() {
    var inMemoryFilmRepository = new InMemoryFilmRepository();

    var result = inMemoryFilmRepository.recupererLesFilms();

    assertThat(result.size()).isEqualTo(22);
  }
}
