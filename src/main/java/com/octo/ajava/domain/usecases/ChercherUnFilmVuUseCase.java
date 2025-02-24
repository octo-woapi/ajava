package com.octo.ajava.domain.usecases;

import com.octo.ajava.domain.FilmVu;
import com.octo.ajava.domain.repositories.FilmVuRepository;
import org.springframework.stereotype.Component;

@Component
public class ChercherUnFilmVuUseCase {

  private final FilmVuRepository filmVuRepository;

  public ChercherUnFilmVuUseCase(FilmVuRepository filmVuRepository) {
    this.filmVuRepository = filmVuRepository;
  }

  public FilmVu executer(int filmVu, String utilisateurId) throws Exception {
    return filmVuRepository.chercherUnFilmVu(filmVu, utilisateurId);
  }
}
