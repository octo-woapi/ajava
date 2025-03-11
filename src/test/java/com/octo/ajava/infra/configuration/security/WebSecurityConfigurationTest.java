package com.octo.ajava.infra.configuration.security;

import static org.apache.http.HttpStatus.SC_UNAUTHORIZED;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

@Import(WebSecurityConfiguration.class)
@WebMvcTest(WebSecurityConfiguration.class)
public class WebSecurityConfigurationTest { // TODO

  @Autowired protected MockMvc mockMvc;

  @Test
  void devrait_renvoyer_http_401_lorsqu_une_route_est_utilisee_sans_authentification()
      throws Exception {
    // When
    MockHttpServletResponse response =
        mockMvc.perform(get("endpoint que je veux tester")).andReturn().getResponse(); // TODO

    // Then
    assertThat(response.getStatus()).isEqualTo(SC_UNAUTHORIZED);
  }
}
