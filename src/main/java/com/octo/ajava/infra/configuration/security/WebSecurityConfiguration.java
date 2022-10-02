package com.octo.ajava.infra.configuration.security;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
public class WebSecurityConfiguration {

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    return http.headers()
        .and()
        .sessionManagement()
        .sessionCreationPolicy(STATELESS)
        .and()
        .authorizeRequests()
        .antMatchers("/basic/**", "/api/films_vus")
        .authenticated()
        .and()
        .httpBasic()
        .and()
        .authorizeRequests()
        .antMatchers("/oauth2/**")
        .authenticated()
        .and()
        .oauth2ResourceServer()
        .jwt()
        .and()
        .and()
        .authorizeRequests()
        .antMatchers("/*")
        .permitAll()
        .and()
        .csrf()
        .disable()
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
}
