package com.octo.ajava.infra.repositories;

import com.octo.ajava.domain.FilmVu;
import com.octo.ajava.domain.repositories.FilmVuRepository;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DatabaseFilmVuRepository implements FilmVuRepository {

  @Autowired private final DatabaseFilmDAO databaseFilmDAO;

  public DatabaseFilmVuRepository(DatabaseFilmDAO databaseFilmDAO) {
    this.databaseFilmDAO = databaseFilmDAO;
  }

  @Override
  @Transactional
  public FilmVu ajouterUnFilmVu(FilmVu filmVu) {
    return this.databaseFilmDAO.save(filmVu);
  }

  @Override
  public List<FilmVu> recupererMesFilmsVus(String userId) {
    return this.databaseFilmDAO.findAllByUtilisateurId(userId);
  }

  @Override
  public void supprimerUnFilmVu() throws Exception {}
}
