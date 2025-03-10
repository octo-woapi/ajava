package com.octo.ajava.domain.usecases;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
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

  @DisplayName("devrait appeler le repository et renvoyer une liste de FilmVu")
  @Test
  void renvoyerFilmVuUtilisateur() throws Exception {
    // Given
    // TODO

    // When
    List<FilmVu> filmVuRenvoyes = recupererMesFilmsVusUseCase.executer("user");

    // Then
    verify(filmVuRepository).chercherDesFilmsVusParUnUtilisateur("user");
    assertThat(filmVuRenvoyes).singleElement().extracting("utilisateurId").isEqualTo("user");
  }
}
