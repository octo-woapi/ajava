package com.octo.ajava.infra.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import com.octo.ajava.ApiIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.mock.web.MockHttpServletResponse;

@WebMvcTest(AccueilBasicAuthController.class)
class AccueilBasicAuthControllerTest extends ApiIntegrationTest {

  private static final String URL_BASIC = "/basic/";

  @Test
  void devrait_renvoyer_http_200_avec_un_message_de_presentation() throws Exception {
    // Given
    String messageAttendu = "Accès avec BASIC AUTH - Bienvenue à la formation AJAVA !";

    // When
    MockHttpServletResponse response =
        mockMvc
            .perform(get(URL_BASIC).with(httpBasic("user", "password")))
            .andReturn()
            .getResponse();

    // Then
    assertThat(response.getStatus()).isEqualTo(OK.value());
    assertThat(response.getContentAsString()).isEqualTo(messageAttendu);
  }
}
