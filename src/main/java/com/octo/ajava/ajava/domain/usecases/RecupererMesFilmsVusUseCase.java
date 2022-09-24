package com.octo.ajava.ajava.domain.usecases;

import com.octo.ajava.ajava.domain.FilmVu;
import com.octo.ajava.ajava.domain.repositories.FilmVuRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RecupererMesFilmsVusUseCase {

    private final FilmVuRepository filmVuRepository;

    RecupererMesFilmsVusUseCase(FilmVuRepository filmVuRepository) {
        this.filmVuRepository = filmVuRepository;
    }

    public List<FilmVu> executer() throws Exception {
        return this.filmVuRepository.recupererMesFilmsVus();
    }
}
