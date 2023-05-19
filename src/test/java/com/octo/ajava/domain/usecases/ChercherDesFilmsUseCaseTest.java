package com.octo.ajava.domain.usecases;

import com.octo.ajava.domain.Film;
import com.octo.ajava.domain.repositories.FilmRepository;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class ChercherDesFilmsUseCaseTest {

  @Mock private FilmRepository filmRepository;

  @InjectMocks private ChercherDesFilmsUseCase chercherDesFilmsUseCase;

  @Test
  public void devrait_retourner_la_liste_des_films() throws Exception {
    // Given
    Film unFilm =
        new Film(
            1,
            "Pulp Fiction",
            "Les vies de deux hommes de main...",
            List.of("Policier", "Drame"),
            LocalDate.of(1994, 9, 23));

    when(filmRepository.chercherDesFilms("pulp fiction")).thenReturn(List.of(unFilm));

    // When
    var result = chercherDesFilmsUseCase.executer("pulp fiction");

    // Then
    assertThat(result.size()).isEqualTo(1);
  }
}
