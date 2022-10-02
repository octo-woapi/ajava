package com.octo.ajava.infra.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.octo.ajava.ApiIntegrationTest;
import com.octo.ajava.domain.Film;
import com.octo.ajava.domain.usecases.ChercherDesFilmsUseCase;
import com.octo.ajava.domain.usecases.RecupererLesFilmsUseCase;
import com.octo.ajava.fixture.FilmFixture;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletResponse;

@WebMvcTest(FilmController.class)
class FilmControllerTest extends ApiIntegrationTest {

  private static final String URL_FILMS = "/api/films";
  private final List<Film> expectedFilms = FilmFixture.uneListeDeFilms();

  @MockBean private RecupererLesFilmsUseCase recupererLesFilmsUseCaseMocked;
  @MockBean private ChercherDesFilmsUseCase chercherDesFilmsUseCaseMocked;

  @Test
  public void doit_renvoyer_le_code_http_200_quand_lister_des_films_a_renvoye_des_resultats()
      throws Exception {
    // given
    when(recupererLesFilmsUseCaseMocked.executer()).thenReturn(expectedFilms);

    // when
    MockHttpServletResponse response =
        mockMvc.perform(get(URL_FILMS).contentType(APPLICATION_JSON)).andReturn().getResponse();

    // then
    assertThat(response.getStatus()).isEqualTo(OK.value());
    assertThat(response.getContentType()).isEqualTo("application/json");
  }

  @Test
  public void doit_renvoyer_dans_body_la_liste_des_films_quand_lister_les_films() throws Exception {
    // given
    when(recupererLesFilmsUseCaseMocked.executer()).thenReturn(expectedFilms);

    // when
    mockMvc
        .perform(get(URL_FILMS).contentType(APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(
            content()
                .json(
                    """
            [
                {
                    "id": 1,
                    "titre": "Pulp Fiction",
                    "synopsis": "Les vies de deux hommes de main...",
                    "genres": ["Policier", "Drame"],
                    "dateDeSortie": "1994-09-23"
                },
                {
                    "id": 2,
                    "titre": "Les Dents de la Mer",
                    "synopsis": "Un shérif local, un biologiste marin et un vieux marin...",
                    "genres": ["Aventure", "Thriller"],
                    "dateDeSortie": "1975-06-20"
                }
            ]
            """));
  }

  @Test
  public void doit_renvoyer_une_liste_vide_en_l_absence_de_films() throws Exception {
    // given
    when(recupererLesFilmsUseCaseMocked.executer()).thenReturn(Collections.emptyList());

    // when
    mockMvc.perform(get(URL_FILMS).contentType(APPLICATION_JSON)).andExpect(content().json("[]"));
  }

  @Test
  public void search_doit_renvoyer_dans_body_la_liste_des_films_recherches() throws Exception {
    // given
    when(chercherDesFilmsUseCaseMocked.executer("batman"))
        .thenReturn(FilmFixture.deuxFilmsRecherchesProvenantDeTMDB());

    // when
    mockMvc
        .perform(get(URL_FILMS + "/search?query=batman").contentType(APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(
            content()
                .json(
                    """
                [
                  {
                    "id": 414906,
                    "titre": "The Batman",
                    "synopsis": "In his second year of fighting crime, Batman...",
                    "genres": [],
                    "dateDeSortie": "2022-03-01"
                  },
                  {
                    "id": 272,
                    "titre": "Batman Begins",
                    "synopsis": "Driven by tragedy, billionaire Bruce Wayne dedicates his life to uncovering and defeating the corruption...",
                    "genres": [],
                    "dateDeSortie": "2005-06-23"
                  }
                ]
                """));
  }
}
