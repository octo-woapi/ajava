package com.octo.ajava.domain.usecases;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import com.octo.ajava.domain.Film;
import com.octo.ajava.domain.repositories.FilmRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class RecupererLesFilmsUseCaseTest {

  @Mock private FilmRepository filmRepository;

  @InjectMocks private RecupererLesFilmsUseCase recupererLesFilmsUseCase;

  @Test
  public void shouldReturnAListOfFilm() throws Exception {
    // Given
    Film unFilm =
        new Film(
            UUID.fromString("5b64010d-8863-496a-b845-89c00a9d6139"),
            "Pulp Fiction",
            "Les vies de deux hommes de main...",
            List.of("Policier", "Drame"),
            LocalDate.of(1994, 9, 23));

    when(filmRepository.recupererLesFilms()).thenReturn(List.of(unFilm));

    // When
    var result = recupererLesFilmsUseCase.executer();

    // Then
    assertThat(result.size()).isEqualTo(1);
  }
}
