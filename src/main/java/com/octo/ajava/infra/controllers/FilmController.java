package com.octo.ajava.infra.controllers;

import com.octo.ajava.domain.Film;
import com.octo.ajava.domain.usecases.RecupererLesFilmsUseCase;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/films")
public class FilmController {

  @Autowired private final RecupererLesFilmsUseCase recupererLesFilmsUseCase;

  FilmController(RecupererLesFilmsUseCase recupererLesFilmsUseCase) {
    this.recupererLesFilmsUseCase = recupererLesFilmsUseCase;
  }

  @GetMapping
  public ResponseEntity<List<Film>> list() throws Exception {
    return ResponseEntity.ok().body(this.recupererLesFilmsUseCase.executer());
  }
}
