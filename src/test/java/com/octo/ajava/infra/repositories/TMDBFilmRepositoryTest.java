package com.octo.ajava.infra.repositories;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.octo.ajava.domain.Film;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TMDBFilmRepositoryTest {

  @Autowired TMDBFilmRepository tmdbFilmRepository;

  @Test
  void recupererLesFilms_devrait_renvoyer_une_liste_de_films_TMDB() throws Exception {
    // When
    List<Film> filmsRecuperes = tmdbFilmRepository.recupererLesFilms();

    // Then
    assertThat(filmsRecuperes).isNotEmpty();
  }
}
