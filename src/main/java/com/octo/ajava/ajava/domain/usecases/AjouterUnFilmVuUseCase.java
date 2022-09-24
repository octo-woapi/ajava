package com.octo.ajava.ajava.domain.usecases;

import com.octo.ajava.ajava.domain.FilmVu;
import com.octo.ajava.ajava.domain.repositories.FilmVuRepository;
import org.springframework.stereotype.Component;

@Component
public class AjouterUnFilmVuUseCase {

    private final FilmVuRepository filmVuRepository;

    AjouterUnFilmVuUseCase(FilmVuRepository filmVuRepository) {
        this.filmVuRepository = filmVuRepository;
    }

    public FilmVu executer() throws Exception {
        return this.filmVuRepository.ajouterUnFilmVu();
    }
}
