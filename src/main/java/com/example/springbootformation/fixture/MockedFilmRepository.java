package com.example.springbootformation.fixture;

import com.example.springbootformation.domain.Film;
import com.example.springbootformation.domain.repositories.FilmRepository;

import java.util.List;

public record MockedFilmRepository() implements FilmRepository {
    @Override
    public List<Film> recupererLesFilms() throws Exception {
        return List.of(new Film("dc2e6bd1-8156-4886-adff-b39e6043af0c", "Spirited Away", "Spirited Away is an Oscar winning Japanese animated film about a ten year old girl who wanders away from her parents along a path that leads to a world ruled by strange and unusual monster-like animals. Her parents have been changed into pigs along with others inside a bathhouse full of these creatures. Will she ever see the world how it once was?"));
    }
}
