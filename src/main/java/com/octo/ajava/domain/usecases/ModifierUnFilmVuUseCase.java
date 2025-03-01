package com.octo.ajava.domain.usecases;

import com.octo.ajava.domain.FilmVu;
import com.octo.ajava.domain.repositories.FilmVuRepository;
import org.springframework.stereotype.Component;

@Component
public class ModifierUnFilmVuUseCase {

  private final FilmVuRepository filmVuRepository;

  public ModifierUnFilmVuUseCase(FilmVuRepository filmVuRepository) {
    this.filmVuRepository = filmVuRepository;
  }

  public FilmVu executer(FilmVu filmVu) throws Exception {
    FilmVu filmVuTrouve =
        filmVuRepository.chercherUnFilmVu(filmVu.getFilmId(), filmVu.getUtilisateurId());

    if (filmVuTrouve == null) {
      return null;
    }

    filmVuTrouve.setNote(filmVu.getNote());
    filmVuTrouve.setCommentaire(filmVu.getCommentaire());
    return filmVuRepository.modifierUnFilmVu(filmVuTrouve);
  }
}
