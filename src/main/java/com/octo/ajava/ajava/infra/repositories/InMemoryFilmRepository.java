package com.octo.ajava.ajava.infra.repositories;

import com.octo.ajava.ajava.domain.Film;
import com.octo.ajava.ajava.domain.repositories.FilmRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InMemoryFilmRepository implements FilmRepository {
    @Override
    public List<Film> recupererLesFilms() throws Exception {
        return null;
    }

    @Override
    public List<Film> recupererLesFilmsAvecPagination() throws Exception {
        return null;
    }

    @Override
    public List<Film> chercherDesFilms() throws Exception {
        return null;
    }
}
