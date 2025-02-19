package com.octo.ajava.infra.repositories;

import com.octo.ajava.domain.FilmVu;
import com.octo.ajava.domain.repositories.FilmVuRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class DatabaseFilmVuRepository implements FilmVuRepository {

  private final DatabaseFilmVuDAO databaseFilmVuDAO;

  public DatabaseFilmVuRepository(DatabaseFilmVuDAO databaseFilmVuDAO) {
    this.databaseFilmVuDAO = databaseFilmVuDAO;
  }

  @Override
  @Transactional
  public FilmVu ajouterUnFilmVu(FilmVu filmVu) {
    return this.databaseFilmVuDAO.save(filmVu);
  }
}
