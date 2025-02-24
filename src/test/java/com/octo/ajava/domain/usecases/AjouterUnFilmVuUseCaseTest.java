package com.octo.ajava.domain.usecases;

import static com.octo.ajava.fixtures.FilmVuTestFixture.unFilmVu;
import static org.assertj.core.api.Assertions.assertThat;

import com.octo.ajava.domain.FilmVu;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AjouterUnFilmVuUseCaseTest { // TODO

  // TODO Mocker le repository
  @InjectMocks private AjouterUnFilmVuUseCase ajouterUnFilmVuUseCase;

  @DisplayName("devrait ajouter un FilmVu et le renvoyer, en appelant le repository")
  @Test
  void ajouterUnFilmVu() {
    // Given
    FilmVu filmVu =
        unFilmVu()
            .avecFilmId(12)
            .avecUtilisateurId("user")
            .avecNote("10/10")
            .avecCommentaire("Le film Batman est spectaculaire")
            .build();
    // TODO Utiliser le repository

    // When
    FilmVu filmVuAjoute = ajouterUnFilmVuUseCase.executer(filmVu); // TODO

    // Then
    // Vérifier que le repository a bien été appelé avec le bon paramètre
    assertThat("TODO").isEqualTo(filmVu); // TODO
  }
}
