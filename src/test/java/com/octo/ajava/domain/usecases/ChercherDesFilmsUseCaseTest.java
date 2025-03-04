package com.octo.ajava.domain.usecases;

import static com.octo.ajava.fixtures.FilmTestFixture.unFilm;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import com.octo.ajava.domain.Film;
import com.octo.ajava.domain.repositories.FilmRepository;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ChercherDesFilmsUseCaseTest {

  @Mock private FilmRepository filmRepository;
  @InjectMocks private ChercherDesFilmsUseCase chercherDesFilmsUseCase;

  @Test
  void devrait_retourner_la_liste_des_films() throws Exception {
    // Given
    Film filmExistant = unFilm().avecId(1).avecTitre("Pulp Fiction").build();

    given(filmRepository.chercherDesFilms(any())).willReturn(List.of(filmExistant));

    // When
    List<Film> filmsTrouves = chercherDesFilmsUseCase.executer("Pulp fiction");

    // Then
    verify(filmRepository).chercherDesFilms("Pulp fiction");
    assertThat(filmsTrouves).singleElement().isEqualTo(filmExistant);
  }
}
