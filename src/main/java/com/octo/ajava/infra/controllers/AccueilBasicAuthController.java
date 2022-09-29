package com.octo.ajava.infra.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/basic/")
public class AccueilBasicAuthController {

  @GetMapping
  public String accueilAvecBasicAuth() {
    return "Accès avec BASIC AUTH - Bienvenue à la formation AJAVA !";
  }
}
