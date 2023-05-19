package com.octo.ajava.domain.usecases;

import com.octo.ajava.domain.Film;
import com.octo.ajava.domain.repositories.FilmRepository;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class ChercherDesFilmsUseCase {

    private final FilmRepository filmRepository;

    ChercherDesFilmsUseCase(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    public List<Film> executer(String query) {
        return this.filmRepository.chercherDesFilms(query);
    }
}
