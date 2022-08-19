package com.example.springbootformation.domain;

import java.util.List;

public interface FilmRepository {
    List<Film> list() throws Exception;
}