package com.example.springbootformation.usecase;

import com.example.springbootformation.domain.Film;
import com.example.springbootformation.domain.FilmRepository;
import com.example.springbootformation.infra.repository.FilmRepositoryFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ListFilmUseCase {

    private FilmRepositoryFactory filmRepositoryFactory;

    ListFilmUseCase(FilmRepositoryFactory filmRepositoryFactory) {
        this.filmRepositoryFactory = filmRepositoryFactory;
    }

    public List<Film> handle() throws Exception {
        return this.filmRepositoryFactory.getFilmRepository().list();
    }
}
