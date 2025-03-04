package com.octo.ajava.infra.controllers;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

import com.octo.ajava.domain.FilmVu;
import com.octo.ajava.domain.usecases.AjouterUnFilmVuUseCase;
import com.octo.ajava.domain.usecases.ChercherUnFilmVuUseCase;
import com.octo.ajava.domain.usecases.ModifierUnFilmVuUseCase;
import com.octo.ajava.domain.usecases.RecupererMesFilmsVusUseCase;
import com.octo.ajava.infra.controllers.entities.CritiqueApi;
import com.octo.ajava.infra.controllers.entities.FilmVuAAjouterApi;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Films Vus", description = "Route public pour la gestion des films vus")
@RequestMapping("/api/films_vus")
public class FilmVuController {

  private final RecupererMesFilmsVusUseCase recupererMesFilmsVusUseCase;
  private final ChercherUnFilmVuUseCase chercherUnFilmVuUseCase;
  private final AjouterUnFilmVuUseCase ajouterUnFilmVuUseCase;
  private final ModifierUnFilmVuUseCase modifierUnFilmVuUseCase;

  FilmVuController(
      RecupererMesFilmsVusUseCase recupererMesFilmsVusUseCase,
      ChercherUnFilmVuUseCase chercherUnFilmVuUseCase,
      AjouterUnFilmVuUseCase ajouterUnFilmVuUseCase,
      ModifierUnFilmVuUseCase modifierUnFilmVuUseCase) {
    this.recupererMesFilmsVusUseCase = recupererMesFilmsVusUseCase;
    this.chercherUnFilmVuUseCase = chercherUnFilmVuUseCase;
    this.ajouterUnFilmVuUseCase = ajouterUnFilmVuUseCase;
    this.modifierUnFilmVuUseCase = modifierUnFilmVuUseCase;
  }

  @GetMapping
  public ResponseEntity<List<FilmVu>> list(Authentication authentication) throws Exception {
    String userId = authentication.getName();
    return ok().body(recupererMesFilmsVusUseCase.executer(userId));
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
    String userId = authentication.getName();

    FilmVu filmVu =
        new FilmVu(
            filmVuAAjouterApi.filmId(),
            userId,
            filmVuAAjouterApi.note(),
            filmVuAAjouterApi.commentaire());
    return status(CREATED).body(ajouterUnFilmVuUseCase.executer(filmVu));
  }

  @PutMapping("/{filmId}")
  public ResponseEntity<FilmVu> modifierFilmVu(
      @PathVariable("filmId") int filmId,
      @RequestBody CritiqueApi critiqueApi,
      Authentication authentication)
      throws Exception {
    FilmVu filmVuModifie =
        modifierUnFilmVuUseCase.executer(
            new FilmVu(
                filmId, authentication.getName(), critiqueApi.note(), critiqueApi.commentaire()));

    if (filmVuModifie == null) {
      return notFound().build();
    }

    return ok().body(filmVuModifie);
  }
}
