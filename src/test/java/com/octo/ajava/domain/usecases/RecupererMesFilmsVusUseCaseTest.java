package com.octo.ajava.domain.usecases;

import static com.octo.ajava.fixtures.FilmVuTestFixture.unFilmVu;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import com.octo.ajava.domain.FilmVu;
import com.octo.ajava.domain.repositories.FilmVuRepository;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RecupererMesFilmsVusUseCaseTest {

  @Mock private FilmVuRepository filmVuRepository;
  @InjectMocks private RecupererMesFilmsVusUseCase recupererMesFilmsVusUseCase;

  @DisplayName("devrait renvoyer la liste des FilmVu par un utilisateur")
  @Test
  void recupererFilmVuParUnUtilisateur() throws Exception {
    // Given
    List<FilmVu> filmsVuParUtilisateur =
        List.of(
            unFilmVu().avecFilmId(1).avecUtilisateurId("user").build(),
            unFilmVu().avecFilmId(5).avecUtilisateurId("user").build());

    given(filmVuRepository.recupererMesFilmsVus(any())).willReturn(filmsVuParUtilisateur);

    // When
    List<FilmVu> filmVuTrouves = recupererMesFilmsVusUseCase.executer("user");

    // Then
    verify(filmVuRepository).recupererMesFilmsVus("user");
    assertThat(filmVuTrouves).hasSize(2).isEqualTo(filmsVuParUtilisateur);
  }
}
