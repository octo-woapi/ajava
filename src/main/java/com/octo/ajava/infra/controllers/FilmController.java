package com.octo.ajava.infra.controllers;

import com.octo.ajava.domain.Film;
import com.octo.ajava.domain.FilmVu;
import com.octo.ajava.domain.usecases.AjouterUnFilmVuUseCase;
import com.octo.ajava.domain.usecases.RecupererLesFilmsUseCase;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Films", description = "Route public pour la gestion des films")
@RequestMapping("/api/films")
public class FilmController {

  @Autowired private final RecupererLesFilmsUseCase recupererLesFilmsUseCase;
  @Autowired private final AjouterUnFilmVuUseCase ajouterUnFilmVuUseCase;

  FilmController(
      RecupererLesFilmsUseCase recupererLesFilmsUseCase,
      AjouterUnFilmVuUseCase ajouterUnFilmVuUseCase) {
    this.recupererLesFilmsUseCase = recupererLesFilmsUseCase;
    this.ajouterUnFilmVuUseCase = ajouterUnFilmVuUseCase;
  }

  @GetMapping
  public ResponseEntity<List<Film>> list() throws Exception {
    return ResponseEntity.ok().body(this.recupererLesFilmsUseCase.executer());
  }

  @PostMapping
  public ResponseEntity<FilmVu> ajouterFilmVu(
      @RequestBody FilmVuAAjouter filmVuAAjouter, @RequestHeader("Authorization") String userId)
      throws Exception {
    return ResponseEntity.ok()
        .body(
            this.ajouterUnFilmVuUseCase.executer(
                new FilmVu(
                    filmVuAAjouter.filmId(),
                    userId,
                    filmVuAAjouter.commentaire(),
                    filmVuAAjouter.note())));
  }
}
