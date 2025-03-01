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
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ChercherUnFilmVuUseCaseTest {

  @Mock private FilmVuRepository filmVuRepository;
  @InjectMocks private ChercherUnFilmVuUseCase chercherUnFilmVuUseCase;

  @DisplayName("devrait chercher un FilmVu et le renvoyer, en appelant le repository")
  @Test
  void chercherUnFilmVu() throws Exception {
    // Given
    FilmVu filmVuExistant =
        unFilmVu()
            .avecFilmId(25)
            .avecUtilisateurId("user")
            .avecNote("10/10")
            .avecCommentaire("Le film Batman est spectaculaire")
            .build();
    given(filmVuRepository.chercherUnFilmVu(anyInt(), any())).willReturn(filmVuExistant);

    // When
    FilmVu filmVuTrouve = chercherUnFilmVuUseCase.executer(25, "user");

    // Then
    verify(filmVuRepository).chercherUnFilmVu(25, "user");
    assertThat(filmVuTrouve).isEqualTo(filmVuExistant);
  }
}
