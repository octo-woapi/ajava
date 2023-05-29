package com.octo.ajava.infra.repositories;

import com.octo.ajava.domain.FilmVu;
import com.octo.ajava.domain.repositories.FilmVuRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DatabaseFilmVuRepository implements FilmVuRepository {

  private final DatabaseFilmDAO databaseFilmDAO;

  public DatabaseFilmVuRepository(DatabaseFilmDAO databaseFilmDAO) {
    this.databaseFilmDAO = databaseFilmDAO;
  }

  @Override
  @Transactional
  public FilmVu ajouterUnFilmVu(FilmVu filmVu) {
    return this.databaseFilmDAO.save(filmVu);
  }
}
