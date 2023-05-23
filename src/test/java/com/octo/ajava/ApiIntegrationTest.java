package com.octo.ajava;

import static com.octo.ajava.AjavaApplication.FORMATEUR_DATE;
import static java.util.Collections.emptyList;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.octo.ajava.infra.configuration.security.WebSecurityConfiguration;
import java.nio.charset.StandardCharsets;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.test.web.servlet.MockMvc;

@Import(WebSecurityConfiguration.class)
public abstract class ApiIntegrationTest {

  private static final String TOKEN = "test-token";
  private static final String BEARER_TOKEN = "Bearer %s".formatted(TOKEN);
  private static final String USER_ID = "user_id";
  private static final String ROLES = "roles";
  private static final String USER_TEST = "usertest";

  @MockBean private JwtDecoder jwtDecoder;

  @Autowired protected MockMvc mockMvc;
  protected ObjectMapper objectMapper;
  protected ObjectWriter objectWriter;

  @BeforeEach
  void setUp() {
    objectMapper = new ObjectMapper();
    JavaTimeModule javaTimeModule = new JavaTimeModule();
    javaTimeModule.addSerializer(new LocalDateSerializer(FORMATEUR_DATE));
    objectMapper.registerModule(javaTimeModule);

    objectWriter = objectMapper.writer().withDefaultPrettyPrinter();
  }

  protected HttpHeaders avecUneAuthentificationValide() {
    return avecAuthentification(USER_TEST, emptyList());
  }

  protected HttpHeaders avecAuthentification(String userId, List<String> roles) {
    given(jwtDecoder.decode(TOKEN))
        .willReturn(
            Jwt.withTokenValue(TOKEN)
                .header("type", "jwt")
                .claim(USER_ID, userId)
                .claim(ROLES, roles)
                .build());
    return headers;
  }

  protected <T> T convertirJsonEnObjet(MockHttpServletResponse response, Class<T> clazz)
      throws Exception {
    return objectMapper.readValue(response.getContentAsString(StandardCharsets.UTF_8), clazz);
  }

  private final HttpHeaders headers = getHeaders();

  private HttpHeaders getHeaders() {
    HttpHeaders headers = new HttpHeaders();
    headers.add(AUTHORIZATION, BEARER_TOKEN);
    return HttpHeaders.readOnlyHttpHeaders(headers);
  }
}
