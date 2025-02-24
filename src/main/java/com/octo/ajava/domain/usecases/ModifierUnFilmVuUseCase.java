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
    // TODO vérifier que le commentaire existe déjà, lever une erreur sinon
    // TODO appeler le repository pour effectuer la modification
  }
}
