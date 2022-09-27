package com.octo.ajava.domain.usecases;

import com.octo.ajava.domain.repositories.FilmVuRepository;
import org.springframework.stereotype.Component;

@Component
public class SupprimerUnFilmVuUseCase {

  private final FilmVuRepository filmVuRepository;

  SupprimerUnFilmVuUseCase(FilmVuRepository filmVuRepository) {
    this.filmVuRepository = filmVuRepository;
  }

  public void executer() throws Exception {
    this.filmVuRepository.supprimerUnFilmVu();
  }
}
