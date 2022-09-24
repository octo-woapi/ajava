package com.example.springbootformation.domain.usecases;

import com.example.springbootformation.domain.FilmVu;
import com.example.springbootformation.domain.repositories.FilmVuRepository;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class RecupererMesFilmsVusUseCase {

  private final FilmVuRepository filmVuRepository;

  RecupererMesFilmsVusUseCase(FilmVuRepository filmVuRepository) {
    this.filmVuRepository = filmVuRepository;
  }

  public List<FilmVu> executer() throws Exception {
    return this.filmVuRepository.recupererMesFilmsVus();
  }
}
