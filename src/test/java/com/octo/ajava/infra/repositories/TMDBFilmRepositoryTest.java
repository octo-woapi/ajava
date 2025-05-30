package com.octo.ajava.infra.repositories;

import static com.octo.ajava.fixtures.FilmTestFixture.deuxFilmsPopulaires;
import static com.octo.ajava.fixtures.FilmTestFixture.deuxFilmsRecherches;
import static com.octo.ajava.fixtures.TMDBMovieTestFixture.deuxFilmsPopulairesVenantDeTMTB;
import static com.octo.ajava.fixtures.TMDBMovieTestFixture.deuxFilmsRecherchesVenantDeTMTB;
import static com.octo.ajava.fixtures.TMDBMovieTestFixture.unTMDBMovie;
import static java.util.Optional.empty;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import com.octo.ajava.domain.Film;
import com.octo.ajava.infra.api_client.TMDBHttpClient;
import com.octo.ajava.infra.api_client.entities.PaginatedTMDBMovies;
import com.octo.ajava.infra.api_client.entities.TMDBMovie;
import com.octo.ajava.infra.mapper.TMDBFilmMapper;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TMDBFilmRepositoryTest {

  @Mock private TMDBHttpClient tmdbHttpClient;
  private TMDBFilmRepository tmdbFilmRepository;

  @BeforeEach
  void setUp() {
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

  @DisplayName("#chercherUnFilmParId")
  @Nested
  class ChercherUnFilmParIdTest {

    @DisplayName("devrait renvoyer un Film trouvé par Id")
    @Test
    void chercherUnFilmParId() {
      // Given
      int id = 414906;
      String titre = "The Batman";

      TMDBMovie tmdbMovie = unTMDBMovie().avecId(id).avecTitle(titre).build();
      given(tmdbHttpClient.chercherUnFilmParId(anyInt())).willReturn(Optional.of(tmdbMovie));

      // When
      Optional<Film> filmTrouve = tmdbFilmRepository.chercherUnFilmParId(id);

      // Then
      verify(tmdbHttpClient).chercherUnFilmParId(id);
      assertThat(filmTrouve).isNotEmpty().get().extracting("id", "titre").contains(id, titre);
    }

    @DisplayName("devrait rien renvoyer lorsque un Film recherché par Id n'a pas été trouvé")
    @Test
    void filmNonTrouve() {
      // Given
      given(tmdbHttpClient.chercherUnFilmParId(anyInt())).willReturn(empty());

      // When
      Optional<Film> filmTrouve = tmdbFilmRepository.chercherUnFilmParId(1);

      // Then
      assertThat(filmTrouve).isEmpty();
    }
  }
}
