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

  public void executer(FilmVu filmVu) throws Exception {
    // TODO appeler le repository pour vérifier que le FilmVu existe déjà (filmId et userId
    // identiques), sinon lever une erreur

    // TODO appeler le repository pour effectuer la modification
  }
}
