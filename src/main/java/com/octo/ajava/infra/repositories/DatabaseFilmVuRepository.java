package com.octo.ajava.infra.repositories;

import com.octo.ajava.domain.FilmVu;
import com.octo.ajava.domain.repositories.FilmVuRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class DatabaseFilmVuRepository implements FilmVuRepository { // TODO

  private final DatabaseFilmVuDAO databaseFilmVuDAO;

  public DatabaseFilmVuRepository(DatabaseFilmVuDAO databaseFilmVuDAO) {
    this.databaseFilmVuDAO = databaseFilmVuDAO;
  }

  @Override
  public FilmVu chercherUnFilmVu(int filmId, String utilisateurId) throws Exception {
    return databaseFilmVuDAO.findByFilmIdAndUtilisateurId(filmId, utilisateurId);
  }

  @Override
  public FilmVu ajouterUnFilmVu(FilmVu filmVu) throws Exception {
    return databaseFilmVuDAO.save(filmVu);
  }

  @Override
  public FilmVu modifierUnFilmVu(FilmVu filmVu) throws Exception {
    return databaseFilmVuDAO.save(filmVu);
  }

  @Override
  public List<FilmVu> chercherDesFilmsVusParUnUtilisateur(String utilisateurId) throws Exception {
    return null; // TODO
  }
}
