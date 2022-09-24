package com.example.springbootformation.usecase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import com.example.springbootformation.domain.Film;
import com.example.springbootformation.domain.repositories.FilmRepository;
import com.example.springbootformation.domain.usecases.RecupererLesFilmsUseCase;
import java.util.Date;
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
            UUID.fromString("dc2e6bd1-8156-4886-adff-b39e6043af0c"),
            "Spirited Away",
            "Spirited Away is an Oscar winning Japanese animated film about a ten year old girl who wanders away from her parents along a path that leads to a world ruled by strange and unusual monster-like animals. Her parents have been changed into pigs along with others inside a bathhouse full of these creatures. Will she ever see the world how it once was?",
            List.of(),
            new Date());

    when(filmRepository.recupererLesFilms()).thenReturn(List.of(unFilm));

    // When
    var result = recupererLesFilmsUseCase.executer();

    // Then
    assertThat(result.size()).isEqualTo(1);
  }
}
