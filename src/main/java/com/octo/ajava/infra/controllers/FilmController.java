package com.octo.ajava.infra.controllers;

import com.octo.ajava.domain.Film;
import com.octo.ajava.domain.usecases.RecupererLesFilmsUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
  @ApiResponses({
    @ApiResponse(
        responseCode = "200",
        description = "La liste de films a bien été renvoyéee",
        content =
            @Content(
                array = @ArraySchema(schema = @Schema(implementation = Film.class)),
                mediaType = "application/json"))
  })
  @GetMapping
  public ResponseEntity<List<Film>> recuperTousLesFilms() {
    return ResponseEntity.ok().body(this.recupererLesFilmsUseCase.executer());
  }
}
