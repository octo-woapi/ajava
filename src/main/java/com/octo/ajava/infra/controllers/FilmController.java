package com.octo.ajava.infra.controllers;

import com.octo.ajava.domain.Film;
import com.octo.ajava.domain.usecases.RecupererLesFilmsUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/films")
@Tag(name = "Film", description = "Services API pour la gestion des films")
public class FilmController {

  private final RecupererLesFilmsUseCase recupererLesFilmsUseCase;

  FilmController(RecupererLesFilmsUseCase recupererLesFilmsUseCase) {
    this.recupererLesFilmsUseCase = recupererLesFilmsUseCase;
  }

  @Operation(summary = "Récupère tous les films")
  @GetMapping
  public ResponseEntity<List<Film>> list() throws Exception {
    return ResponseEntity.ok().body(this.recupererLesFilmsUseCase.executer());
  }
}
