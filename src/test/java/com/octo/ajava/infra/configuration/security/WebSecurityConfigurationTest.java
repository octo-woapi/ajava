package com.octo.ajava.infra.configuration.security;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import com.octo.ajava.infra.controllers.AccueilController;
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
  void devrait_renvoyer_http_401_lorsque_la_route_basic_est_utilisee_sans_authentification()
      throws Exception {
    // When
    MockHttpServletResponse response = mockMvc.perform(get("/basic/")).andReturn().getResponse();

    // Then
    assertThat(response.getStatus()).isEqualTo(UNAUTHORIZED.value());
  }
}
