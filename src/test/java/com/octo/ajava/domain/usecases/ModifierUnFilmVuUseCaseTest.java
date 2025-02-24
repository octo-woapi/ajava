package com.octo.ajava.domain.usecases;

import static com.octo.ajava.fixtures.FilmVuTestFixture.unFilmVu;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import com.octo.ajava.domain.FilmVu;
import com.octo.ajava.domain.repositories.FilmVuRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ModifierUnFilmVuUseCaseTest { // TODO

  private static final int FILM_ID = 12;
  private static final String UTILISATEUR_ID = "user";

  @Mock private FilmVuRepository filmVuRepository;
  @Captor private ArgumentCaptor<FilmVu> filmVuCaptor;

  @InjectMocks private ModifierUnFilmVuUseCase modifierUnFilmVuUseCase;

  @DisplayName(
      "devrait appeler le repository pour vérifier que le FilmVu existe déjà, et ensuite pour le modifier")
  @Test
  void modifierUnFilmVu() throws Exception {
    // Given
    FilmVu filmVuExistant =
        unFilmVu().avecFilmId(FILM_ID).avecUtilisateurId(UTILISATEUR_ID).build();
    FilmVu filmVuModifie =
        unFilmVu()
            .avecFilmId(FILM_ID)
            .avecUtilisateurId(UTILISATEUR_ID)
            .avecNote("05/10")
            .avecCommentaire("Batman c'est moyen")
            .build();

    given(filmVuRepository.chercherUnFilmVu(anyInt(), any())).willReturn(null); // TODO corriger

    // When
    modifierUnFilmVuUseCase.executer(filmVuModifie);

    // Then
    verify(filmVuRepository).chercherUnFilmVu(12, UTILISATEUR_ID);

    // TODO verify(...)... vérifier que les paramètres passés à la méthode
    // repository.modifierUnFilmVu() sont corrects, en utilisant ..capture()
  }

  @DisplayName("devrait renvoyer null si le FilmVu (filmId et utilisateurId) n'existe pas")
  @Test
  void filmVuNonExistant() throws Exception {
    // Given
    FilmVu filmVuModifie =
        unFilmVu()
            .avecFilmId(FILM_ID)
            .avecUtilisateurId(UTILISATEUR_ID)
            .avecNote("05/10")
            .avecCommentaire("Batman c'est moyen")
            .build();

    // TODO mocker la méthode filmVuRepository.chercherUnFilmVu()
    // given(filmVuRepository.chercherUnFilmVu( ...

    // When
    FilmVu filmVuRenvoye = modifierUnFilmVuUseCase.executer(filmVuModifie);

    // Then
    // TODO assertion pour vérifier que le film vu renvoyé est null
  }
}
