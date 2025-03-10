package com.octo.ajava.domain.usecases;

import static com.octo.ajava.fixtures.FilmTestFixture.unFilm;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import com.octo.ajava.domain.Film;
import com.octo.ajava.domain.repositories.FilmRepository;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RecupererLesFilmsUseCaseTest {

  @Mock private FilmRepository filmRepository;
  @InjectMocks private RecupererLesFilmsUseCase recupererLesFilmsUseCase;

  @DisplayName("devrait appeler le repository pour récupérer une liste de films")
  @Test
  void recupererLesFilms() throws Exception {
    // Given
    Film unFilm = unFilm().avecTitre("Pulp Fiction").build();
    given(filmRepository.recupererLesFilms()).willReturn(List.of(unFilm));

    // When
    List<Film> filmsRenvoyes = recupererLesFilmsUseCase.executer();

    // Then
    assertThat(filmsRenvoyes).singleElement().extracting("titre").isEqualTo("Pulp Fiction");
  }
}
