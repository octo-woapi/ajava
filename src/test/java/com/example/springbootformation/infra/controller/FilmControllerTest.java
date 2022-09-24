package com.example.springbootformation.infra.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import com.example.springbootformation.ApiIntegrationTest;
import com.example.springbootformation.domain.Film;
import com.example.springbootformation.domain.usecases.RecupererLesFilmsUseCase;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletResponse;

@WebMvcTest(FilmController.class)
public class FilmControllerTest extends ApiIntegrationTest {

  @MockBean private RecupererLesFilmsUseCase recupererLesFilmsUseCase;

  @Test
  void devrait_renvoyer_http_401_quand_la_route_est_appelee_sans_authentification()
      throws Exception {
    laRouteEstProtegee(get("/api/films"));
  }

  @Test
  public void devrait_renvoyer_http_200_avec_une_liste_de_films() throws Exception {
    // Given
    Film unFilm = new Film(UUID.randomUUID(), "", "", List.of(), new Date());
    when(recupererLesFilmsUseCase.executer()).thenReturn(List.of(unFilm));

    // When
    MockHttpServletResponse response =
        mockMvc
            .perform(get("/api/films").with(httpBasic("user", "password")))
            .andReturn()
            .getResponse();

    // Then
    assertThat(response.getStatus()).isEqualTo(OK.value());

    Film[] filmsRenvoyes = objectMapper.readValue(response.getContentAsString(), Film[].class);
    assertThat(filmsRenvoyes).isNotEmpty().hasSize(1);
    assertThat(filmsRenvoyes[0]).isEqualTo(unFilm);
  }
}
