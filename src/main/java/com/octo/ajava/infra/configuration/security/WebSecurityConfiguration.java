package com.octo.ajava.infra.configuration.security;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.SessionManagementConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class WebSecurityConfiguration {

  // avec keykloack
  //  @Bean
  //  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
  //    return http.headers(withDefaults())
  //        .sessionManagement(WebSecurityConfiguration::statelessSessionManagement)
  //        .authorizeHttpRequests(
  //            authorize -> authorize.requestMatchers("/basic/**",
  // "/api/films_vus").authenticated())
  //        .httpBasic(withDefaults())
  //        .formLogin(withDefaults())
  //        // .and()
  //        .authorizeHttpRequests(authorize ->
  // authorize.requestMatchers("/oauth2/**").authenticated())
  //        .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
  //        .authorizeHttpRequests(auth -> auth.anyRequest().permitAll())
  //        .csrf(AbstractHttpConfigurer::disable)
  //        .build();
  //  }

  // avec basic auth
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    return http.headers(withDefaults())
        .sessionManagement(WebSecurityConfiguration::statelessSessionManagement)
        .authorizeHttpRequests(
            authorize -> authorize.requestMatchers("/api/films_vus").hasAnyRole("USER"))
        .httpBasic(withDefaults())
        .authorizeHttpRequests(auth -> auth.anyRequest().permitAll())
        .csrf(AbstractHttpConfigurer::disable)
        .build();
  }

  @Bean
  public InMemoryUserDetailsManager userDetailsService() {
    final UserDetails utilisateurLambda =
        User.withUsername("user")
            .password(passwordEncoder().encode("password"))
            .roles("USER")
            .build();
    final UserDetails jeanDurant =
        User.withUsername("jdurant")
            .password(passwordEncoder().encode("password"))
            .roles("USER")
            .build();
    final UserDetails admin =
        User.withUsername("admin")
            .password(passwordEncoder().encode("password"))
            .roles("ADMIN", "USER")
            .build();

    return new InMemoryUserDetailsManager(utilisateurLambda, admin, jeanDurant);
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder(8);
  }

  private static void statelessSessionManagement(
      SessionManagementConfigurer<HttpSecurity> sessionManager) {
    sessionManager.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
  }
}
