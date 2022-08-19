package com.example.springbootformation.service;

import com.example.springbootformation.domain.Film;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmService {
    public List<Film> list() {
        return List.of();
    }
}
