package com.octo.ajava.infra.controllers;

import com.octo.ajava.domain.Film;
import com.octo.ajava.domain.FilmVu;
import com.octo.ajava.domain.usecases.AjouterUnFilmVuUseCase;
import com.octo.ajava.domain.usecases.RecupererLesFilmsUseCase;
import java.util.List;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/films")
public class FilmController {

  @Autowired
  private final RecupererLesFilmsUseCase recupererLesFilmsUseCase;
  @Autowired
  private final AjouterUnFilmVuUseCase ajouterUnFilmVuUseCase;

  FilmController(RecupererLesFilmsUseCase recupererLesFilmsUseCase, AjouterUnFilmVuUseCase ajouterUnFilmVuUseCase) {
    this.recupererLesFilmsUseCase = recupererLesFilmsUseCase;
    this.ajouterUnFilmVuUseCase = ajouterUnFilmVuUseCase;
  }

  @GetMapping
  public ResponseEntity<List<Film>> list() throws Exception {
    return ResponseEntity.ok().body(this.recupererLesFilmsUseCase.executer());
  }

  @PostMapping
  public void ajouterFilmVu(@RequestBody FilmVuAAjouter filmVuAAjouter, @RequestHeader("Authorization") String userId) throws Exception {
    this.ajouterUnFilmVuUseCase.executer(new FilmVu(filmVuAAjouter.filmId(), userId, filmVuAAjouter.commentaire(), filmVuAAjouter.note()));
  }
}
