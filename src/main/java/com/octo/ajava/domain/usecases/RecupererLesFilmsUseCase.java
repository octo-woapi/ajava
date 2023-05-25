package com.octo.ajava.domain.usecases;

import com.octo.ajava.domain.Film;
import com.octo.ajava.domain.repositories.FilmRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RecupererLesFilmsUseCase {

  private final FilmRepository filmRepository;

  RecupererLesFilmsUseCase(FilmRepository filmRepository) {
    this.filmRepository = filmRepository;
  }

  public List<Film> executer() {
    return filmRepository.recupererLesFilms();
  }
}