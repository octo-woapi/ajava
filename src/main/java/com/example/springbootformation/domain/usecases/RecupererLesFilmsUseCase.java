package com.example.springbootformation.domain.usecases;

import com.example.springbootformation.domain.Film;
import com.example.springbootformation.domain.repositories.FilmRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RecupererLesFilmsUseCase {

    private final FilmRepository filmRepository;

    RecupererLesFilmsUseCase(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    public List<Film> executer() throws Exception {
        return this.filmRepository.recupererLesFilms();
    }
}
