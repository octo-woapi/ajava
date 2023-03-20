package com.octo.ajava.infra.controllers;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.HttpStatus.OK;

import com.octo.ajava.domain.Film;
import com.octo.ajava.domain.usecases.RecupererLesFilmsUseCase;
import com.octo.ajava.fixture.FilmFixture;
import java.util.List;
import java.util.Objects;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class FilmControllerUTest {

  @InjectMocks private FilmController filmController;
  @Mock private RecupererLesFilmsUseCase recupererLesFilmsUseCase;

  @Test
  void lister_devrait_renvoyer_uneliste_de_film() throws Exception {
    // Given
    List<Film> listDeFilmsAttendue = FilmFixture.uneListeDeFilms();
    given(recupererLesFilmsUseCase.executer()).willReturn(listDeFilmsAttendue);

    // When
    ResponseEntity<List<Film>> response = filmController.list();

    // Then
    assertThat(response.getStatusCode()).isEqualTo(OK);
    List<Film> listeDeFilms = Objects.requireNonNull(response.getBody());
    assertThat(listeDeFilms.size()).isEqualTo(listDeFilmsAttendue.size());
  }
}
