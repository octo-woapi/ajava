package com.octo.ajava;

import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mock;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;

@TestConfiguration
public class AjavaApplicationTest {

  private static final String TOKEN = "test-token";
  private static final String USER_ID = "user_id";
  private static final Object USER_TEST = "usertest";

  @Bean
  public JwtDecoder getJwtDecoder() {
    JwtDecoder jwtDecoder = mock(JwtDecoder.class);
    lenient()
        .when(jwtDecoder.decode(TOKEN))
        .thenReturn(
            Jwt.withTokenValue(TOKEN).header("type", "jwt").claim(USER_ID, USER_TEST).build());
    return jwtDecoder;
  }
}
