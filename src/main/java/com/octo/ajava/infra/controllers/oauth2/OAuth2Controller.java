package com.octo.ajava.infra.controllers.oauth2;

import com.octo.ajava.infra.controllers.UtilisateurApi;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(
    name = "OAuth 2.0 Authentification",
    description = "Route protégée par une authentification OAuth2")
@RequestMapping("/oauth2/")
public class OAuth2Controller {

  @GetMapping
  public String accueilAvecOAuth2() {
    return "Accès avec OAUTH 2.0 - Bienvenue à la formation AJAVA !";
  }

  @GetMapping("user/me")
  public ResponseEntity<UtilisateurApi> informationUtilisateur(Authentication authentication)
      throws Exception {
    Jwt jwtToken = ((JwtAuthenticationToken) authentication).getToken();
    String username = jwtToken.getClaims().get("user_id").toString();
    List<String> roles = (List<String>) jwtToken.getClaims().get("roles");
    return ResponseEntity.ok().body(new UtilisateurApi(username, roles));
  }
}
