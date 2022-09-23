package com.example.springbootformation.infra.controller;

import com.example.springbootformation.domain.Film;
import com.example.springbootformation.domain.usecases.RecupererLesFilmsUseCase;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/films")
public class FilmController {

    private RecupererLesFilmsUseCase recupererLesFilmsUseCase;

    FilmController(RecupererLesFilmsUseCase recupererLesFilmsUseCase) {
        this.recupererLesFilmsUseCase = recupererLesFilmsUseCase;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Film>> list() throws Exception {
        return ResponseEntity.ok().body(this.recupererLesFilmsUseCase.executer());
    }

    @GetMapping(value = "/{filmId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Film> getById(@PathVariable String filmId) {
        return ResponseEntity.ok().body(new Film("dc2e6bd1-8156-4886-adff-b39e6043af0c", "Spirited Away", "Spirited Away is an Oscar winning Japanese animated film about a ten year old girl who wanders away from her parents along a path that leads to a world ruled by strange and unusual monster-like animals. Her parents have been changed into pigs along with others inside a bathhouse full of these creatures. Will she ever see the world how it once was?"));
    }

    @PostMapping()
    public ResponseEntity updateMyFavoriteFilm(@RequestBody Film film) {
        return ResponseEntity.status(201).build();
    }
}
