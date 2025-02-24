package com.octo.ajava.domain.usecases;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import com.octo.ajava.domain.Film;
import com.octo.ajava.domain.repositories.FilmRepository;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RecupererLesFilmsUseCaseTest {

  @Mock private FilmRepository filmRepository;
  @InjectMocks private RecupererLesFilmsUseCase recupererLesFilmsUseCase;

  @Test
  void devrait_retourner_la_liste_des_films() throws Exception {
    // Given
    Film unFilm =
        new Film(
            1,
            "Pulp Fiction",
            "Les vies de deux hommes de main...",
            List.of("Policier", "Drame"),
            LocalDate.of(1994, 9, 23));

    given(filmRepository.recupererLesFilms()).willReturn(List.of(unFilm));

    // When
    List<Film> result = recupererLesFilmsUseCase.executer();

    // Then
    assertThat(result).hasSize(1);
  }
}
