package com.example.springbootformation.infra.security;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import com.example.springbootformation.infra.controller.AccueilController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(WebSecurityConfiguration.class)
@Import(AccueilController.class)
public class WebSecurityConfigurationTest {

  @Autowired protected MockMvc mockMvc;

  @Test
  void devrait_renvoyer_http_401_sur_une_route_securisee_utilisee_sans_authentification()
      throws Exception {
    // When
    MockHttpServletResponse response = mockMvc.perform(get("/api/")).andReturn().getResponse();

    // Then
    assertThat(response.getStatus()).isEqualTo(UNAUTHORIZED.value());
  }
}
