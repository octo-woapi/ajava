package com.octo.ajava.infra.controllers;

import com.octo.ajava.domain.FilmVu;
import com.octo.ajava.domain.usecases.AjouterUnFilmVuUseCase;
import com.octo.ajava.infra.controllers.entities.FilmVuAAjouterApi;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

  @PostMapping
  public ResponseEntity<FilmVu> ajouterFilmVu(
          @RequestBody FilmVuAAjouterApi filmVuAAjouterApi, Authentication authentication)
          throws Exception {
    return null;
  }
}
