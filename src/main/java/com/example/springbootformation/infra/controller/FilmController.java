package com.example.springbootformation.infra.controller;

import com.example.springbootformation.domain.Film;
import com.example.springbootformation.domain.usecases.RecupererLesFilmsUseCase;
import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/films")
public class FilmController {

  private final RecupererLesFilmsUseCase recupererLesFilmsUseCase;

  FilmController(RecupererLesFilmsUseCase recupererLesFilmsUseCase) {
    this.recupererLesFilmsUseCase = recupererLesFilmsUseCase;
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<Film>> list() throws Exception {
    return ResponseEntity.ok().body(this.recupererLesFilmsUseCase.executer());
  }
}
