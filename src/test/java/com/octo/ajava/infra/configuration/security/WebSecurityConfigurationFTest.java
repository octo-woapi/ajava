package com.octo.ajava.infra.configuration.security;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class WebSecurityConfigurationFTest {

  @Autowired private MockMvc mockMvc;

  @Test
  void devrait_renvoyer_une_200_sur_les_routes_swagger_ui() throws Exception {
    // When
    MockHttpServletResponse response =
        mockMvc.perform(get("/swagger-ui/index.html")).andReturn().getResponse();

    // Then
    assertThat(response.getStatus()).isEqualTo(OK.value());
  }
}
