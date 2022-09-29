package com.octo.ajava.infra.repositories;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class InMemoryFilmRepositoryTest {

    @Test
    public void doit_retourner_une_liste_de_film() {
        var inMemoryFilmRepository = new InMemoryFilmRepository();

        var result = inMemoryFilmRepository.recupererLesFilms();

        assertThat(result.size()).isEqualTo(22);
    }
}