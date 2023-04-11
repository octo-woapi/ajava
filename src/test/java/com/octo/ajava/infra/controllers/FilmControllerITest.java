package com.octo.ajava.infra.controllers;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.octo.ajava.domain.Film;
import com.octo.ajava.domain.usecases.RecupererLesFilmsUseCase;
import com.octo.ajava.fixture.FilmFixture;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(FilmController.class)
class FilmControllerITest {

  @Autowired private MockMvc mockMvc;
  @Autowired private ObjectMapper objectMapper;

  @MockBean private RecupererLesFilmsUseCase recupererLesFilmsUseCase;

  @Test
  void recuperTousLesFilms_devrait_renvoyer_une_HTTP_200_et_une_liste_de_film() throws Exception {
    // Given
    List<Film> listDeFilmsAttendue = FilmFixture.uneListeDeFilms();
    given(recupererLesFilmsUseCase.executer()).willReturn(listDeFilmsAttendue);

    // When
    MockHttpServletResponse response = mockMvc.perform(get("/api/films")).andReturn().getResponse();

    // Then
    Film[] listeDeFilms = objectMapper.readValue(response.getContentAsString(), Film[].class);

    assertThat(response.getStatus()).isEqualTo(OK.value());
    assertThat(listeDeFilms.length).isEqualTo(listDeFilmsAttendue.size());
    assertThat(Arrays.stream(listeDeFilms).findFirst().get()).isEqualTo(listDeFilmsAttendue.get(0));
  }
}
