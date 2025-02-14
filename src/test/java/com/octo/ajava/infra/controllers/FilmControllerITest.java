package com.octo.ajava.infra.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import com.octo.ajava.domain.exceptions.FilmNotFoundException;
import com.octo.ajava.domain.usecases.ChercherDesFilmsUseCase;
import com.octo.ajava.domain.usecases.ChercherUnFilmParIdUseCase;
import com.octo.ajava.domain.usecases.RecupererLesFilmsUseCase;
import com.octo.ajava.infra.configuration.security.WebSecurityConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@Import(WebSecurityConfiguration.class)
@WebMvcTest(FilmController.class)
class FilmControllerITest {

  @Autowired private MockMvc mockMvc;

  @MockitoBean private ChercherUnFilmParIdUseCase chercherUnFilmParIdUseCase;

  @MockitoBean private ChercherDesFilmsUseCase chercherDesFilmsUseCase;

  @MockitoBean private RecupererLesFilmsUseCase recupererLesFilmsUseCase;

  @Test
  public void doit_renvoyer_une_erreur_404_quand_le_film_demande_nest_pas_trouve()
      throws Exception {
    // Given
    String bodyAttendu = "{\"message\":\"La ressource demandée 1 n'a pas été trouvée\"}";
    when(chercherUnFilmParIdUseCase.executer("1")).thenThrow(new FilmNotFoundException("1"));

    // When
    MockHttpServletResponse response =
        mockMvc.perform(MockMvcRequestBuilders.get("/api/films/1")).andReturn().getResponse();

    // Then
    assertThat(response.getStatus()).isEqualTo(NOT_FOUND.value());
    assertThat(response.getContentAsByteArray()).asString().isEqualTo(bodyAttendu);
  }
}
