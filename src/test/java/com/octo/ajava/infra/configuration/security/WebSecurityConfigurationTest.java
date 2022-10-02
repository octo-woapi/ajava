package com.octo.ajava.infra.configuration.security;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import com.octo.ajava.ApiIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.mock.web.MockHttpServletResponse;

@WebMvcTest(WebSecurityConfiguration.class)
public class WebSecurityConfigurationTest extends ApiIntegrationTest {

  @Test
  void devrait_renvoyer_http_401_lorsque_la_route_basic_est_utilisee_sans_authentification()
      throws Exception {
    // When
    MockHttpServletResponse response = mockMvc.perform(get("/basic/")).andReturn().getResponse();

    // Then
    assertThat(response.getStatus()).isEqualTo(UNAUTHORIZED.value());
  }

  @Test
  void devrait_renvoyer_http_401_lorsque_la_route_oauth2_est_utilisee_sans_authentification()
      throws Exception {
    // When
    MockHttpServletResponse response = mockMvc.perform(get("/oauth2/")).andReturn().getResponse();

    // Then
    assertThat(response.getStatus()).isEqualTo(UNAUTHORIZED.value());
  }
}
