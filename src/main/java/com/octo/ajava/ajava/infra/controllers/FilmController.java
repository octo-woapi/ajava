package com.octo.ajava.ajava.infra.controllers;

import com.octo.ajava.ajava.domain.Film;
import com.octo.ajava.ajava.domain.usecases.RecupererLesFilmsUseCase;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
