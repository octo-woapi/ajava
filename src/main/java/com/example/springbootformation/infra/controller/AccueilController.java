package com.example.springbootformation.infra.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accueil")
public class AccueilController {

  @GetMapping
  public String accueil() {
    return "Bienvenue à la formation AJAVA.";
  }
}
