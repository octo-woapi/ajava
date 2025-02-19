package com.octo.ajava.domain.usecases;

import com.octo.ajava.domain.Film;
import com.octo.ajava.domain.exceptions.FilmNotFoundException;
import com.octo.ajava.domain.repositories.FilmRepository;
import org.springframework.stereotype.Component;

@Component
public class ChercherUnFilmParIdUseCase {

  private final FilmRepository filmRepository;

  ChercherUnFilmParIdUseCase(FilmRepository filmRepository) {
    this.filmRepository = filmRepository;
  }

  public Film executer(String id) throws FilmNotFoundException {
    return null;
  }
}
