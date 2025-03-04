package com.octo.ajava.domain.usecases;

import com.octo.ajava.domain.Film;
import com.octo.ajava.domain.exceptions.FilmNotFoundException;
import com.octo.ajava.domain.repositories.FilmRepository;
import org.springframework.stereotype.Component;

@Component
public class ChercherUnFilmParIdUseCase {

  private final FilmRepository filmRepository;

  public ChercherUnFilmParIdUseCase(FilmRepository filmRepository) {
    this.filmRepository = filmRepository;
  }

  public Film executer(int id) throws FilmNotFoundException {
    var response = this.filmRepository.chercherUnFilmParId(id);

    if (response.isPresent()) {
      return response.get();
    }
    throw new FilmNotFoundException(id);
  }
}
