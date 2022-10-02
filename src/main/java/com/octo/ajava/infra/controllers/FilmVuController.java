package com.octo.ajava.infra.controllers;

import com.octo.ajava.domain.FilmVu;
import com.octo.ajava.domain.usecases.AjouterUnFilmVuUseCase;
import com.octo.ajava.domain.usecases.RecupererMesFilmsVusUseCase;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Films Vus", description = "Route public pour la gestion des films vus")
@RequestMapping("/api/film_vus")
public class FilmVuController {

  @Autowired private final RecupererMesFilmsVusUseCase recupererMesFilmsVusUseCase;
  @Autowired private final AjouterUnFilmVuUseCase ajouterUnFilmVuUseCase;

  FilmVuController(
      RecupererMesFilmsVusUseCase recupererMesFilmsVusUseCase,
      AjouterUnFilmVuUseCase ajouterUnFilmVuUseCase) {
    this.recupererMesFilmsVusUseCase = recupererMesFilmsVusUseCase;
    this.ajouterUnFilmVuUseCase = ajouterUnFilmVuUseCase;
  }

  @GetMapping
  public ResponseEntity<List<FilmVu>> list(@RequestHeader("Authorization") String userId)
      throws Exception {
    return ResponseEntity.ok().body(this.recupererMesFilmsVusUseCase.executer(userId));
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
