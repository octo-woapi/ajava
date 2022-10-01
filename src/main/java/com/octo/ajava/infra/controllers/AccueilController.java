package com.octo.ajava.infra.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Accueil", description = "Route public")
@RequestMapping("/")
public class AccueilController {

  @GetMapping
  public String accueil() {
    return "Bienvenue à la formation AJAVA !";
  }
}
