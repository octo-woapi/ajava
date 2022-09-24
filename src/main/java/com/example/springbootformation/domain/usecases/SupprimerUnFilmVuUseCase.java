package com.example.springbootformation.domain.usecases;

import com.example.springbootformation.domain.repositories.FilmVuRepository;
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
