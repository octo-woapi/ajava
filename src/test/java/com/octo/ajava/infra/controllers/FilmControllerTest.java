package com.octo.ajava.infra.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.octo.ajava.domain.Film;
import com.octo.ajava.domain.exceptions.FilmNotFoundException;
import com.octo.ajava.domain.usecases.RecupererLesFilmsUseCase;
import com.octo.ajava.fixture.FilmFixture;
import com.octo.ajava.utils.JsonMapperForTesting;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(FilmController.class)
class FilmControllerTest {

  @Autowired private MockMvc mockMvc;
  @MockBean private RecupererLesFilmsUseCase recupererLesFilmsUseCaseMocked;

  private final List<Film> expectedFilms = FilmFixture.uneListeDeFilms();

  @Test
  public void doit_renvoyer_le_code_http_200_quand_lister_des_films_a_renvoye_des_resultats()
      throws Exception {
    // given
    when(recupererLesFilmsUseCaseMocked.executer()).thenReturn(expectedFilms);
    // when-then
    mockMvc.perform(get("/films")).andDo(print()).andExpect(status().isOk());
  }

  @Test
  public void doit_renvoyer_dans_body_la_liste_des_films_quand_lister_les_films() throws Exception {
    // given
    when(recupererLesFilmsUseCaseMocked.executer()).thenReturn(expectedFilms);
    // when-then
    mockMvc
        .perform(get("/films"))
        .andDo(print())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.length()").value(2))
        .andExpect(content().json(JsonMapperForTesting.toJson(expectedFilms)));
  }

  @Test
  public void doit_renvoyer_une_liste_vide_quand_lister_des_films_a_aucun_resultat()
      throws Exception {
    // given
    when(recupererLesFilmsUseCaseMocked.executer()).thenReturn(Collections.emptyList());
    // when-then
    mockMvc
        .perform(get("/films"))
        .andDo(print())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.length()").value(0))
        .andExpect(content().json("[]"));
  }

  @Test
  public void doit_renvoyer_une_erreur_404_quand_le_film_demande_nest_pas_trouve()
      throws Exception {
    // given
    String bodyAttendu = "{\"message\":\"La ressource demandée Batman n'a pas été trouvée\"}";
    when(recupererLesFilmsUseCaseMocked.executer()).thenThrow(new FilmNotFoundException("Batman"));
    // when-then
    mockMvc
        .perform(get("/films"))
        .andDo(print())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(content().json(bodyAttendu));
  }
}
