package com.octo.ajava.infra.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import com.octo.ajava.ApiIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.mock.web.MockHttpServletResponse;

@WebMvcTest(ApiController.class)
class ApiControllerTest extends ApiIntegrationTest {

  @Test
  void devrait_renvoyer_http_401_quand_la_route_est_appelee_sans_authentification()
      throws Exception {
    laRouteEstProtegee(get("/api/"));
  }

  @Test
  void devrait_renvoyer_http_200_avec_un_message_de_presentation() throws Exception {
    // Given
    String messageAttendu = "Présentation de l'API Films";

    // When
    MockHttpServletResponse response =
        mockMvc.perform(get("/api/").with(httpBasic("user", "password"))).andReturn().getResponse();

    // Then
    assertThat(response.getStatus()).isEqualTo(OK.value());
    assertThat(response.getContentAsString()).isEqualTo(messageAttendu);
  }
}
