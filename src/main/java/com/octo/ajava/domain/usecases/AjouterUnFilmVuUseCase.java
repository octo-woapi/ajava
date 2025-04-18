package com.octo.ajava.domain.usecases;

import com.octo.ajava.domain.FilmVu;
import com.octo.ajava.domain.repositories.FilmVuRepository;
import org.springframework.stereotype.Component;

@Component
public class AjouterUnFilmVuUseCase {

  private final FilmVuRepository filmVuRepository;

  public AjouterUnFilmVuUseCase(FilmVuRepository filmVuRepository) {
    this.filmVuRepository = filmVuRepository;
  }

  public FilmVu executer(FilmVu filmVu) throws Exception {
    return filmVuRepository.ajouterUnFilmVu(filmVu);
  }
}
