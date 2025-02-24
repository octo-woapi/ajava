package com.octo.ajava.infra.controllers;

import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;

import com.octo.ajava.domain.FilmVu;
import com.octo.ajava.domain.usecases.AjouterUnFilmVuUseCase;
import com.octo.ajava.domain.usecases.ChercherUnFilmVuUseCase;
import com.octo.ajava.infra.controllers.entities.FilmVuAAjouterApi;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Films Vus", description = "Route public pour la gestion des films vus")
@RequestMapping("/api/films_vus")
public class FilmVuController { // TDO

  private final ChercherUnFilmVuUseCase chercherUnFilmVuUseCase;
  private final AjouterUnFilmVuUseCase ajouterUnFilmVuUseCase;

  public FilmVuController(
      ChercherUnFilmVuUseCase chercherUnFilmVuUseCase,
      AjouterUnFilmVuUseCase ajouterUnFilmVuUseCase) {
    this.chercherUnFilmVuUseCase = chercherUnFilmVuUseCase;
    this.ajouterUnFilmVuUseCase = ajouterUnFilmVuUseCase;
  }

  @GetMapping("/{filmId}")
  public ResponseEntity<FilmVu> chercherUnFilmVu(
      @PathVariable("filmId") int filmId, Authentication authentication) throws Exception {
    FilmVu filmVu = chercherUnFilmVuUseCase.executer(filmId, authentication.getName());

    if (filmVu == null) {
      return notFound().build();
    }

    return ok().body(filmVu);
  }

  @PostMapping
  public ResponseEntity<FilmVu> ajouterFilmVu(
      @RequestBody FilmVuAAjouterApi filmVuAAjouterApi, Authentication authentication)
      throws Exception {
    return null; // TODO
  }
}
