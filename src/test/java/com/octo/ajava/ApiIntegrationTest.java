package com.octo.ajava;

import com.octo.ajava.infra.configuration.security.WebSecurityConfiguration;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Import(WebSecurityConfiguration.class)
public abstract class ApiIntegrationTest {

  private static final String TOKEN = "test-token";
  private static final String BEARER_TOKEN = "Bearer %s".formatted(TOKEN);
  private static final String USER_ID = "user_id";
  private static final String ROLES = "roles";
  private static final String USER_TEST = "usertest";

  private final HttpHeaders headers = getHeaders();

  @Autowired protected MockMvc mockMvc;
  @MockBean private JwtDecoder jwtDecoder;

  private HttpHeaders getHeaders() {
    HttpHeaders headers = new HttpHeaders();
    headers.add(AUTHORIZATION, BEARER_TOKEN);
    return HttpHeaders.readOnlyHttpHeaders(headers);
  }

  protected void laRouteEstProtegee(MockHttpServletRequestBuilder route) throws Exception {
    mockMvc.perform(route).andExpect(status().isUnauthorized());
  }

  protected HttpHeaders avecAuthentification() {
    return avecAuthentification(USER_TEST, List.of());
  }

  protected HttpHeaders avecAuthentification(String userId, List<String> roles) {
    when(jwtDecoder.decode(TOKEN))
        .thenReturn(
            Jwt.withTokenValue(TOKEN)
                .header("type", "jwt")
                .claim(USER_ID, userId)
                .claim(ROLES, roles)
                .build());
    return headers;
  }

  protected <T> T convertirJsonEnObjet(MockHttpServletResponse response, Class<T> clazz)
      throws Exception {
    return ObjectMapperBuilder.handle().readValue(response.getContentAsString(StandardCharsets.UTF_8), clazz);
  }
}
