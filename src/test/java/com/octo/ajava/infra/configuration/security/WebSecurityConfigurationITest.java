package com.octo.ajava.infra.configuration.security;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(WebSecurityConfiguration.class)
public class WebSecurityConfigurationITest {

  @Autowired private MockMvc mockMvc;

  @Test
  void devrait_renvoyer_http_401_lorsque_la_route_api_films_est_utilisee_sans_authentification()
      throws Exception {
    // When
    MockHttpServletResponse response = mockMvc.perform(get("/api/films")).andReturn().getResponse();

    // Then
    assertThat(response.getStatus()).isEqualTo(UNAUTHORIZED.value());
  }

  @Test
  void devrait_renvoyer_http_404_lorsque_la_route_est_inconnue() throws Exception {
    // When
    MockHttpServletResponse response =
        mockMvc.perform(get("/route/inconnue")).andReturn().getResponse();

    // Then
    assertThat(response.getStatus()).isEqualTo(NOT_FOUND.value());
  }
}
