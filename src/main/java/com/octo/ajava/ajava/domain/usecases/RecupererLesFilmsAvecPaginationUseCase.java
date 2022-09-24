package com.octo.ajava.ajava.domain.usecases;

import com.octo.ajava.ajava.domain.Film;
import com.octo.ajava.ajava.domain.repositories.FilmRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RecupererLesFilmsAvecPaginationUseCase {

    private final FilmRepository filmRepository;

    RecupererLesFilmsAvecPaginationUseCase(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    public List<Film> executer() throws Exception {
        return this.filmRepository.recupererLesFilmsAvecPagination();
    }
}
