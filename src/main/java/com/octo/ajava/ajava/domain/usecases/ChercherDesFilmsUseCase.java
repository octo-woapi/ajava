package com.octo.ajava.ajava.domain.usecases;


import com.octo.ajava.ajava.domain.Film;
import com.octo.ajava.ajava.domain.repositories.FilmRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ChercherDesFilmsUseCase {

    private final FilmRepository filmRepository;

    ChercherDesFilmsUseCase(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    public List<Film> executer() throws Exception {
        return this.filmRepository.chercherDesFilms();
    }
}
