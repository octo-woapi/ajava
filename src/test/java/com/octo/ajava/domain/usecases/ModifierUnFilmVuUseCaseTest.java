package com.octo.ajava.domain.usecases;

import static com.octo.ajava.fixtures.FilmVuTestFixture.unFilmVu;
import static org.assertj.core.api.Assertions.assertThat;
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
class ModifierUnFilmVuUseCaseTest {

  private static final int FILM_ID = 12;
  private static final String UTILISATEUR_ID = "user";

  @Captor private ArgumentCaptor<FilmVu> filmVuCaptor;

  @Mock private FilmVuRepository filmVuRepository;
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

    given(filmVuRepository.chercherUnFilmVu(anyInt(), any())).willReturn(filmVuExistant);

    // When
    modifierUnFilmVuUseCase.executer(filmVuModifie);

    // Then
    verify(filmVuRepository).chercherUnFilmVu(FILM_ID, UTILISATEUR_ID);

    verify(filmVuRepository).modifierUnFilmVu(filmVuCaptor.capture());
    FilmVu filmVu = filmVuCaptor.getValue();

    assertThat(filmVu.getFilmId()).isEqualTo(FILM_ID);
    assertThat(filmVu.getUtilisateurId()).isEqualTo(UTILISATEUR_ID);
    assertThat(filmVu.getNote()).isEqualTo("05/10");
    assertThat(filmVu.getCommentaire()).isEqualTo("Batman c'est moyen");
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

    given(filmVuRepository.chercherUnFilmVu(anyInt(), any())).willReturn(null);

    // When
    FilmVu filmVuRenvoye = modifierUnFilmVuUseCase.executer(filmVuModifie);

    // Then
    assertThat(filmVuRenvoye).isNull();
  }
}
