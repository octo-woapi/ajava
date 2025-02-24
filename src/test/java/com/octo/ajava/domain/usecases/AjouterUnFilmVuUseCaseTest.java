package com.octo.ajava.domain.usecases;

import static org.assertj.core.api.Assertions.assertThat;

import com.octo.ajava.domain.FilmVu;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AjouterUnFilmVuUseCaseTest {

  // TODO Utiliser le repository
  @InjectMocks private AjouterUnFilmVuUseCase ajouterUnFilmVuUseCase;

  @DisplayName("devrait ajouter un FilmVu et le renvoyer, en appelant le repository")
  @Test
  void ajouterUnFilmVu() {
    // Given
    FilmVu filmVu = new FilmVu(12, "user", "10/10", "Le film Batman est spectaculaire");
    // TODO Utiliser le repository

    // When
    ajouterUnFilmVuUseCase.executer(filmVu); // TODO

    // Then
    assertThat("TODO").isEqualTo(filmVu); // TODO
  }
}
