package com.octo.ajava.infra.repositories;

import com.octo.ajava.domain.FilmVu;
import com.octo.ajava.domain.exceptions.FilmNotFoundException;
import com.octo.ajava.domain.repositories.FilmVuRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class DatabaseFilmVuRepository implements FilmVuRepository {

  @Override
  public FilmVu ajouterUnFilmVu() throws Exception {
    throw new FilmNotFoundException("TITRE_VIDE");
  }

  @Override
  public List<FilmVu> recupererMesFilmsVus() throws Exception {
    return null;
  }

  @Override
  public void supprimerUnFilmVu() throws Exception {}
}
