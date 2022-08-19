package com.example.springbootformation.infra.repository;

import com.example.springbootformation.domain.Film;
import com.example.springbootformation.domain.FilmRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public class TheMovieDatabaseApiRepository implements FilmRepository {
    @Override
    public List<Film> list() throws Exception {
        throw new Exception("Not implemented method");
    }
}
