package com.octo.ajava.infra.configuration.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.SessionManagementConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

@EnableWebSecurity
@Configuration
public class WebSecurityConfiguration {

  private static final RequestMatcher API_REQUESTS_MATCHER =
      AntPathRequestMatcher.antMatcher("/api/**");
  private static final RequestMatcher SWAGGER_REQUESTS_MATCHER =
      AntPathRequestMatcher.antMatcher("/swagger-ui/**");

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    return http.securityMatcher(
            request ->
                API_REQUESTS_MATCHER.matches(request) || SWAGGER_REQUESTS_MATCHER.matches(request))
        .headers(Customizer.withDefaults())
        .sessionManagement(WebSecurityConfiguration::statelessSessionManagement)
        .authorizeHttpRequests(auth -> auth.anyRequest().permitAll())
        .csrf(AbstractHttpConfigurer::disable)
        .build();
  }

  private static void statelessSessionManagement(
      SessionManagementConfigurer<HttpSecurity> session) {
    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
  }
}
