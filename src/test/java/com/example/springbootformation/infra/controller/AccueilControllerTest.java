package com.example.springbootformation.infra.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import com.example.springbootformation.ApiIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.mock.web.MockHttpServletResponse;

@WebMvcTest(AccueilController.class)
class AccueilControllerTest extends ApiIntegrationTest {

  @Test
  void devrait_renvoyer_http_200_et_un_message_d_accueil() throws Exception {
    // Given
    String texteAttendu = "Bienvenue à la formation AJAVA.";
    // When
    MockHttpServletResponse response = mockMvc.perform(get("/accueil")).andReturn().getResponse();

    // Then
    assertThat(response.getStatus()).isEqualTo(OK.value());
    assertThat(response.getContentAsString()).isEqualTo(texteAttendu);
  }
}
