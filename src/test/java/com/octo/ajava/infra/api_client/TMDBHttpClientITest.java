package com.octo.ajava.infra.api_client;

import static org.assertj.core.api.Assertions.assertThat;

import com.octo.ajava.infra.api_client.entities.TMDBMovie;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TMDBHttpClientITest {

  @Autowired private TMDBHttpClient tmdbHttpClient;

  @Test
  void recupererLesFilmsPopulaires_devrait_renvoyer_une_liste_de_films_TMDB() {
    // When
    List<TMDBMovie> filmsRecuperes = tmdbHttpClient.recupererLesFilmsPopulaires();

    // Then
    assertThat(filmsRecuperes).isNotEmpty();
    TMDBMovie premierFilm = filmsRecuperes.get(0);
    assertThat(premierFilm.getTitle()).isNotNull();
    assertThat(premierFilm.getOverview()).isNotNull();
    assertThat(premierFilm.getReleaseDate()).isNotNull();
  }
}
