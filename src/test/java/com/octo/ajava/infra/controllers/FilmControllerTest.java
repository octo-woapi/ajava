package com.octo.ajava.infra.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import com.octo.ajava.ApiIntegrationTest;
import com.octo.ajava.domain.Film;
import com.octo.ajava.domain.exceptions.FilmNotFoundException;
import com.octo.ajava.domain.usecases.RecupererLesFilmsUseCase;
import com.octo.ajava.fixture.FilmFixture;
import com.octo.ajava.infra.errorHandling.MessageErreurApi;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest(FilmController.class)
class FilmControllerTest extends ApiIntegrationTest {

  private static final String URL_FILMS = "/api/films";
  @MockBean private RecupererLesFilmsUseCase recupererLesFilmsUseCaseMocked;

  private final List<Film> expectedFilms = FilmFixture.uneListeDeFilms();

  @Test
  void devrait_renvoyer_http_401_quand_la_route_est_appelee_sans_authentification()
      throws Exception {
    laRouteEstProtegee(MockMvcRequestBuilders.get(URL_FILMS));
  }

  @Test
  public void doit_renvoyer_le_code_http_200_quand_lister_des_films_a_renvoye_des_resultats()
      throws Exception {
    // given
    when(recupererLesFilmsUseCaseMocked.executer()).thenReturn(expectedFilms);

    // when
    MockHttpServletResponse response =
        mockMvc
            .perform(
                get(URL_FILMS).with(httpBasic("user", "password")).contentType(APPLICATION_JSON))
            .andReturn()
            .getResponse();

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
        mockMvc
            .perform(
                get(URL_FILMS).with(httpBasic("user", "password")).contentType(APPLICATION_JSON))
            .andReturn()
            .getResponse();

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
        mockMvc
            .perform(
                get(URL_FILMS).with(httpBasic("user", "password")).contentType(APPLICATION_JSON))
            .andReturn()
            .getResponse();

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
        mockMvc
            .perform(
                get(URL_FILMS).with(httpBasic("user", "password")).contentType(APPLICATION_JSON))
            .andReturn()
            .getResponse();

    // then
    assertThat(response.getStatus()).isEqualTo(NOT_FOUND.value());
    MessageErreurApi erreurRenvoyee = convertirJsonEnObjet(response, MessageErreurApi.class);
    assertThat(erreurRenvoyee.message()).isEqualTo(messageErreurAttendu);
  }
}
