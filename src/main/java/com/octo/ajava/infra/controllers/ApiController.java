package com.octo.ajava.infra.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
public class ApiController {

  @GetMapping
  public String presentationDeLApi() {
    return "Présentation de l'API Films";
  }
}
