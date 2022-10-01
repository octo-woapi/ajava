package com.octo.ajava.infra.controllers;

import com.octo.ajava.domain.FilmVu;
import com.octo.ajava.domain.usecases.RecupererMesFilmsVusUseCase;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Films Vus", description = "Route public pour la gestion des films vus")
@RequestMapping("/api/film_vus")
public class FilmVuController {

  @Autowired private final RecupererMesFilmsVusUseCase recupererMesFilmsVusUseCase;

  FilmVuController(RecupererMesFilmsVusUseCase recupererMesFilmsVusUseCase) {
    this.recupererMesFilmsVusUseCase = recupererMesFilmsVusUseCase;
  }

  @GetMapping
  public ResponseEntity<List<FilmVu>> list(@RequestHeader("Authorization") String userId)
      throws Exception {
    return ResponseEntity.ok().body(this.recupererMesFilmsVusUseCase.executer(userId));
  }
}
