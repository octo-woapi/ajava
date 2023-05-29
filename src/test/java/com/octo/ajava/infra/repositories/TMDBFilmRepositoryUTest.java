package com.octo.ajava.infra.repositories;

import com.octo.ajava.domain.Film;
import com.octo.ajava.fixture.FilmFixture;
import com.octo.ajava.fixture.TMDBMovieFixture;
import com.octo.ajava.infra.api_client.TMDBHttpClient;
import com.octo.ajava.infra.api_client.entities.PaginatedTMDBMovies;
import com.octo.ajava.infra.api_client.entities.TMDBMovie;
import com.octo.ajava.infra.mapper.TMDBFilmMapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.BDDMockito.given;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class TMDBFilmRepositoryUTest {

    private TMDBFilmRepository tmdbFilmRepository;

    @Mock
    private TMDBHttpClient tmdbHttpClient;

    private TMDBFilmMapper tmdbFilmMapper = new TMDBFilmMapper();

    @BeforeEach
    public void setUp() {
        tmdbFilmRepository = new TMDBFilmRepository(
                tmdbHttpClient,
                tmdbFilmMapper
        );
    }

    @Test
    void recupererLesFilms_retourne_une_liste_de_films_TMDB() {
        // Given
        given(tmdbHttpClient.recupererLesFilmsPopulaires()).willReturn(new PaginatedTMDBMovies(
                1,
                TMDBMovieFixture.deuxFilmsPopulairesVenantDeTMTB(),
                2,
                1
        ));

        // When
        var result = tmdbFilmRepository.recupererLesFilms();

        // Then
        Assertions.assertEquals(FilmFixture.deuxFilmsPopulaires(), result);
    }

    @Test
    void rechercherDesFilms_retourne_une_liste_de_films() {
        // Given
        given(tmdbHttpClient.chercherDesFilms("batman")).willReturn(new PaginatedTMDBMovies(
                1,
                TMDBMovieFixture.deuxFilmsRecherchesVenantDeTMTB(),
                2,
                1
        ));

        // When
        var result = tmdbFilmRepository.chercherDesFilms("batman");

        // Then
        Assertions.assertEquals(FilmFixture.deuxFilmsRecherches(), result);
    }

    @Test
    void chercherUnFilmParId_retourne_un_film_quand_il_est_present_dans_la_reponse() {
        // Given
        given(tmdbHttpClient.chercherUnFilmParId("414906")).willReturn(Optional.of(
                new TMDBMovie(
                        414906,
                        "The Batman",
                        "en",
                        "The Batman",
                        "In his second year of fighting crime, Batman uncovers corruption in Gotham City that connects to his own family while facing a serial killer known as the Riddler.",
                        LocalDate.of(2022, 3, 1),
                        153,
                        8
                )
        ));

        // When
        var result = tmdbFilmRepository.chercherUnFilmParId("414906");

        // Then
        var expected = Optional.of(
                new Film(
                        414906,
                        "The Batman",
                        "In his second year of fighting crime, Batman uncovers corruption in Gotham City that connects to his own family while facing a serial killer known as the Riddler.",
                        List.of(),
                        LocalDate.of(2022, 3, 1)
                )
        );
        Assertions.assertEquals(expected, result);
    }



    @Test
    void chercherUnFilmParId_retourne_empty_quand_il_n_est_present_pas_dans_la_reponse() {
        // Given
        given(tmdbHttpClient.chercherUnFilmParId("1")).willReturn(Optional.empty());

        // When
        var result = tmdbFilmRepository.chercherUnFilmParId("1");

        // Then
        var expected = Optional.empty();
        Assertions.assertEquals(expected, result);
    }
}
