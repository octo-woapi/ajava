package com.octo.ajava.infra.controllers;

import static org.springframework.http.ResponseEntity.ok;

import com.octo.ajava.domain.Film;
import com.octo.ajava.domain.usecases.ChercherDesFilmsUseCase;
import com.octo.ajava.domain.usecases.ChercherUnFilmParIdUseCase;
import com.octo.ajava.domain.usecases.RecupererLesFilmsUseCase;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Films", description = "Route public pour la gestion des films")
@RequestMapping("/api/films")
public class FilmController {

  private final RecupererLesFilmsUseCase recupererLesFilmsUseCase;
  private final ChercherDesFilmsUseCase chercherDesFilmsUseCase;
  private final ChercherUnFilmParIdUseCase chercherUnFilmParIdUseCase;

  FilmController(
      RecupererLesFilmsUseCase recupererLesFilmsUseCase,
      ChercherDesFilmsUseCase chercherDesFilmsUseCase,
      ChercherUnFilmParIdUseCase chercherUnFilmParIdUseCase) {
    this.recupererLesFilmsUseCase = recupererLesFilmsUseCase;
    this.chercherDesFilmsUseCase = chercherDesFilmsUseCase;
    this.chercherUnFilmParIdUseCase = chercherUnFilmParIdUseCase;
  }

  @GetMapping
  public ResponseEntity<List<Film>> list() throws Exception {
    return ok().body(recupererLesFilmsUseCase.executer());
  }

  @GetMapping("/search")
  public ResponseEntity<List<Film>> search(@RequestParam String query) throws Exception {
    return ok().body(chercherDesFilmsUseCase.executer(query));
  }

  @GetMapping("/{id}")
  public ResponseEntity<Film> getById(@PathVariable int id) throws Exception {
    return ok().body(chercherUnFilmParIdUseCase.executer(id));
  }
}
