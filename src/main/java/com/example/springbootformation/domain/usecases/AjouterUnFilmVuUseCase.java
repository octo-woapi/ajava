package com.example.springbootformation.domain.usecases;

import com.example.springbootformation.domain.FilmVu;
import com.example.springbootformation.domain.repositories.FilmVuRepository;
import org.springframework.stereotype.Component;

@Component
public class AjouterUnFilmVuUseCase {

  private final FilmVuRepository filmVuRepository;

  AjouterUnFilmVuUseCase(FilmVuRepository filmVuRepository) {
    this.filmVuRepository = filmVuRepository;
  }

  public FilmVu executer() throws Exception {
    return this.filmVuRepository.ajouterUnFilmVu();
  }
}
