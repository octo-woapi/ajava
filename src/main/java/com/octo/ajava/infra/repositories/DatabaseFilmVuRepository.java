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
  public FilmVu chercherUnFilmVu(int filmId, String utilisateurId) throws Exception {
    return databaseFilmVuDAO.findByFilmIdAndUtilisateurId(filmId, utilisateurId);
  }

  @Override
  @Transactional
  public FilmVu ajouterUnFilmVu(FilmVu filmVu) {
    return databaseFilmVuDAO.save(filmVu);
  }

  @Override
  @Transactional
  public Void modifierUnFilmVu(FilmVu filmVu) throws Exception {
    return null;
  }
}
