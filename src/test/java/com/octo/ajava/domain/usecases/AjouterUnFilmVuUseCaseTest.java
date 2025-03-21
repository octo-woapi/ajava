package com.octo.ajava.domain.usecases;

import static com.octo.ajava.fixtures.FilmVuTestFixture.unFilmVu;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import com.octo.ajava.domain.FilmVu;
import com.octo.ajava.domain.repositories.FilmVuRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AjouterUnFilmVuUseCaseTest {

  @Mock private FilmVuRepository filmVuRepository;
  @InjectMocks private AjouterUnFilmVuUseCase ajouterUnFilmVuUseCase;

  @DisplayName("devrait ajouter un FilmVu et le renvoyer, en appelant le repository")
  @Test
  void ajouterUnFilmVu() throws Exception {
    // Given
    FilmVu filmVu =
        unFilmVu()
            .avecFilmId(12)
            .avecUtilisateurId("user")
            .avecNote("10/10")
            .avecCommentaire("Le film Batman est spectaculaire")
            .build();
    given(filmVuRepository.ajouterUnFilmVu(any())).willReturn(filmVu);

    // When
    FilmVu filmVuAjoute = ajouterUnFilmVuUseCase.executer(filmVu);

    // Then
    verify(filmVuRepository).ajouterUnFilmVu(filmVu);
    assertThat(filmVuAjoute).isEqualTo(filmVu);
  }
}
