package com.octo.ajava.infra.controllers.basic_auth;

import com.octo.ajava.infra.controllers.UtilisateurApi;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(
    name = "Basic Authentification",
    description = "Route protégée par une authentification Basic Auth")
@RequestMapping("/basic/")
public class BasicAuthController {

  @GetMapping
  public String accueilAvecBasicAuth() {
    return "Accès avec BASIC AUTH - Bienvenue à la formation AJAVA !";
  }

  @GetMapping("user/me")
  public ResponseEntity<UtilisateurApi> informationUtilisateur(Authentication authentication)
      throws Exception {
    String username = authentication.getName();
    List<String> roles =
        authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
    return ResponseEntity.ok().body(new UtilisateurApi(username, roles));
  }
}
