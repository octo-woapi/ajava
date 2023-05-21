package com.octo.ajava.infra.controllers;

import com.octo.ajava.domain.usecases.AjouterUnFilmVuUseCase;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Films Vus", description = "Route public pour la gestion des films vus")
@RequestMapping("/api/films_vus")
public class FilmVuController {

  private final AjouterUnFilmVuUseCase ajouterUnFilmVuUseCase;

  FilmVuController(
      AjouterUnFilmVuUseCase ajouterUnFilmVuUseCase) {
    this.ajouterUnFilmVuUseCase = ajouterUnFilmVuUseCase;
  }
}
