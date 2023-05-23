package com.octo.ajava.infra.configuration.security;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.SessionManagementConfigurer;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

@EnableWebSecurity
@Configuration
public class WebSecurityConfiguration {

  private static final RequestMatcher ROUTES_API_FILMS_MATCHER =
      AntPathRequestMatcher.antMatcher("/api/**");
  private static final RequestMatcher ROUTES_SWAGGER_UI_MATCHER =
      AntPathRequestMatcher.antMatcher("/swagger-ui/**");

  @Bean
  @Order(Ordered.HIGHEST_PRECEDENCE)
  public SecurityFilterChain filterChainPourSwagger_UI(HttpSecurity http) throws Exception {
    return http.securityMatcher(matcherDesRoutesSwaggerAutorisees())
        .headers(Customizer.withDefaults())
        .sessionManagement(WebSecurityConfiguration::statelessSessionManagement)
        .authorizeHttpRequests(auth -> auth.anyRequest().permitAll())
        .csrf(AbstractHttpConfigurer::disable)
        .build();
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    return http.securityMatcher(matcherDesRoutesApiAutorisees())
        .headers(withDefaults())
        .sessionManagement(WebSecurityConfiguration::statelessSessionManagement)
        .authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
        .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
        .csrf(AbstractHttpConfigurer::disable)
        .build();
  }

  private static void statelessSessionManagement(
      SessionManagementConfigurer<HttpSecurity> sessionManager) {
    sessionManager.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
  }

  private static RequestMatcher matcherDesRoutesApiAutorisees() {
    return request -> ROUTES_API_FILMS_MATCHER.matches(request);
  }

  private static RequestMatcher matcherDesRoutesSwaggerAutorisees() {
    return request -> ROUTES_SWAGGER_UI_MATCHER.matches(request);
  }

  @EnableMethodSecurity
  @SuppressWarnings("unused")
  static class ActivationPreAuthorize {}
}
