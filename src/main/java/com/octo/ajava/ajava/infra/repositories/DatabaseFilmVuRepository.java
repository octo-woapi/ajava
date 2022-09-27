package com.octo.ajava.ajava.infra.repositories;

import com.octo.ajava.ajava.domain.FilmVu;
import com.octo.ajava.ajava.domain.exceptions.FilmNotFoundException;
import com.octo.ajava.ajava.domain.repositories.FilmVuRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public void supprimerUnFilmVu() throws Exception {

    }
}
