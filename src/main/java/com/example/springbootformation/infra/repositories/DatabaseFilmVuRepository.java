package com.example.springbootformation.infra.repositories;

import com.example.springbootformation.domain.FilmVu;
import com.example.springbootformation.domain.repositories.FilmVuRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DatabaseFilmVuRepository implements FilmVuRepository {

    @Override
    public FilmVu ajouterUnFilmVu() throws Exception {
        return null;
    }

    @Override
    public List<FilmVu> recupererMesFilmsVus() throws Exception {
        return null;
    }

    @Override
    public void supprimerUnFilmVu() throws Exception {

    }
}
