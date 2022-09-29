package com.octo.ajava.infra.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.octo.ajava.ApiIntegrationTest;
import com.octo.ajava.domain.Film;
import com.octo.ajava.domain.FilmVu;
import com.octo.ajava.domain.exceptions.FilmNotFoundException;
import com.octo.ajava.domain.usecases.AjouterUnFilmVuUseCase;
import com.octo.ajava.domain.usecases.RecupererLesFilmsUseCase;
import com.octo.ajava.fixture.FilmFixture;
import com.octo.ajava.infra.errorHandling.MessageErreurApi;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;

@WebMvcTest(FilmController.class)
class FilmControllerTest extends ApiIntegrationTest {

  @MockBean
  private RecupererLesFilmsUseCase recupererLesFilmsUseCaseMocked;
  @MockBean
  private AjouterUnFilmVuUseCase ajouterUnFilmVuUseCaseMocked;
  private static final String URL_FILMS = "/api/films";

  private final List<Film> expectedFilms = FilmFixture.uneListeDeFilms();

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
    MockHttpServletResponse response =
        mockMvc.perform(get(URL_FILMS).contentType(APPLICATION_JSON)).andReturn().getResponse();

    // then
    Film[] filmsRenvoyes = convertirJsonEnObjet(response, Film[].class);
    assertThat(filmsRenvoyes).isNotEmpty().hasSize(2).hasSameElementsAs(expectedFilms);
  }

  @Test
  public void doit_renvoyer_une_liste_vide_en_l_absence_de_films() throws Exception {
    // given
    when(recupererLesFilmsUseCaseMocked.executer()).thenReturn(Collections.emptyList());

    // when
    MockHttpServletResponse response =
        mockMvc.perform(get(URL_FILMS).contentType(APPLICATION_JSON)).andReturn().getResponse();

    // then
    Film[] filmsRenvoyes = convertirJsonEnObjet(response, Film[].class);
    assertThat(filmsRenvoyes).isEmpty();
  }

  @Test
  public void doit_renvoyer_une_erreur_404_quand_le_film_demande_nest_pas_trouve()
      throws Exception {
    // given
    String messageErreurAttendu = "La ressource demandée Batman n'a pas été trouvée";

    when(recupererLesFilmsUseCaseMocked.executer()).thenThrow(new FilmNotFoundException("Batman"));

    // when
    MockHttpServletResponse response =
        mockMvc.perform(get(URL_FILMS).contentType(APPLICATION_JSON)).andReturn().getResponse();

    // then
    assertThat(response.getStatus()).isEqualTo(NOT_FOUND.value());
    MessageErreurApi erreurRenvoyee = convertirJsonEnObjet(response, MessageErreurApi.class);
    assertThat(erreurRenvoyee.message()).isEqualTo(messageErreurAttendu);
  }

  @Test
  public void quand_j_ajoute_un_film_vu_renvoi_une_200_quand_tout_se_passe_bien() throws Exception {
    // given
    var filmVu = new FilmVu(1, "Basic dXNlcjpwYXNzd29yZA==", "10/10", "Batman c'est ouf");
    when(ajouterUnFilmVuUseCaseMocked.executer(filmVu)).thenReturn(filmVu);

    // when
    MockHttpServletResponse response =
        mockMvc.perform(
            post(URL_FILMS)
                .with(httpBasic("user", "password"))
                .contentType(APPLICATION_JSON)
                .content("""
                    {
                      "filmId": 1,
                      "note": "10/10",
                      "commentaire": "azaz"
                    }
                    """)
                .accept(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

    // then
    assertThat(response.getStatus()).isEqualTo(OK.value());
    assertThat(response.getContentType()).isEqualTo("application/json");
  }
}
