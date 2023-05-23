package com.octo.ajava.infra.controllers;

import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import com.octo.ajava.ApiIntegrationTest;
import com.octo.ajava.domain.Film;
import com.octo.ajava.domain.usecases.RecupererLesFilmsUseCase;
import com.octo.ajava.fixture.FilmFixture;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletResponse;

@WebMvcTest(FilmController.class)
class FilmControllerITest extends ApiIntegrationTest {

  private static final String ROUTE_API_FILMS_URL = "/api/films";

  @MockBean private RecupererLesFilmsUseCase recupererLesFilmsUseCase;

  @Test
  void devrait_renvoyer_http_401_quand_la_route_api_films_est_appelee_sans_authentification()
      throws Exception {
    // When
    MockHttpServletResponse response =
        mockMvc.perform(get(ROUTE_API_FILMS_URL)).andReturn().getResponse();

    // Then
    assertThat(response.getStatus()).isEqualTo(UNAUTHORIZED.value());
  }

  @Test
  void
      devrait_renvoyer_http_200_quand_la_route_api_films_est_appelee_avec_une_authentification_valide()
          throws Exception {
    // Given
    given(recupererLesFilmsUseCase.executer()).willReturn(emptyList());
    Film[] filmsAttendus = new Film[0];

    // When
    MockHttpServletResponse response =
        mockMvc
            .perform(
                get(ROUTE_API_FILMS_URL)
                    .headers(avecUneAuthentificationValide())
                    .contentType(APPLICATION_JSON))
            .andReturn()
            .getResponse();

    // Then
    assertThat(response.getStatus()).isEqualTo(OK.value());

    Film[] filmsRenvoyes = convertirJsonEnObjet(response, Film[].class);
    assertThat(filmsRenvoyes).isEqualTo(filmsAttendus);
  }

  @Test
  void recuperTousLesFilms_devrait_renvoyer_une_HTTP_200_et_une_liste_de_film() throws Exception {
    // Given
    List<Film> listDeFilmsAttendue = FilmFixture.uneListeDeFilms();
    given(recupererLesFilmsUseCase.executer()).willReturn(listDeFilmsAttendue);

    // When
    MockHttpServletResponse response =
        mockMvc
            .perform(
                get(ROUTE_API_FILMS_URL)
                    .headers(avecUneAuthentificationValide())
                    .contentType(APPLICATION_JSON))
            .andReturn()
            .getResponse();

    // Then
    assertThat(response.getStatus()).isEqualTo(OK.value());

    Film[] filmsRenvoyes = convertirJsonEnObjet(response, Film[].class);
    assertThat(filmsRenvoyes.length).isEqualTo(listDeFilmsAttendue.size());
    assertThat(Arrays.stream(filmsRenvoyes).findFirst().get())
        .isEqualTo(listDeFilmsAttendue.get(0));
  }
}
