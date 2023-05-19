package com.octo.ajava.infra.controllers;

import com.octo.ajava.domain.FilmVu;
import com.octo.ajava.domain.usecases.AjouterUnFilmVuUseCase;
import com.octo.ajava.domain.usecases.RecupererMesFilmsVusUseCase;
import com.octo.ajava.infra.controllers.entities.FilmVuAAjouterApi;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Films Vus", description = "Route public pour la gestion des films vus")
@RequestMapping("/api/films_vus")
public class FilmVuController {

  private final RecupererMesFilmsVusUseCase recupererMesFilmsVusUseCase;
  private final AjouterUnFilmVuUseCase ajouterUnFilmVuUseCase;

  FilmVuController(
      RecupererMesFilmsVusUseCase recupererMesFilmsVusUseCase,
      AjouterUnFilmVuUseCase ajouterUnFilmVuUseCase) {
    this.recupererMesFilmsVusUseCase = recupererMesFilmsVusUseCase;
    this.ajouterUnFilmVuUseCase = ajouterUnFilmVuUseCase;
  }

  @GetMapping
  public ResponseEntity<List<FilmVu>> list(Authentication authentication) throws Exception {
    String userId = authentication.getName();
    return ResponseEntity.ok().body(this.recupererMesFilmsVusUseCase.executer(userId));
  }

  @PostMapping
  public ResponseEntity<FilmVu> ajouterFilmVu(
          @RequestBody FilmVuAAjouterApi filmVuAAjouterApi, Authentication authentication)
      throws Exception {
    String userId = authentication.getName();

    return ResponseEntity.ok()
        .body(
            this.ajouterUnFilmVuUseCase.executer(
                new FilmVu(
                    filmVuAAjouterApi.filmId(),
                    userId,
                    filmVuAAjouterApi.note(),
                    filmVuAAjouterApi.commentaire())));
  }
}
