package com.octo.ajava.domain.usecases;

import com.octo.ajava.domain.FilmVu;
import com.octo.ajava.domain.repositories.FilmVuRepository;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class RecupererMesFilmsVusUseCase { // TODO

  private final FilmVuRepository filmVuRepository;

  public RecupererMesFilmsVusUseCase(FilmVuRepository filmVuRepository) {
    this.filmVuRepository = filmVuRepository;
  }

  public List<FilmVu> executer(String userId) throws Exception {
    return null; // TODO Utiliser le repository
  }
}
