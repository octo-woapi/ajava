package com.octo.ajava.infra.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(
    name = "Basic Authentification",
    description = "Route protégée par une authentification Basic Auth")
@RequestMapping("/basic/")
public class AccueilBasicAuthController {

  @GetMapping
  public String accueilAvecBasicAuth() {
    return "Accès avec BASIC AUTH - Bienvenue à la formation AJAVA !";
  }
}
