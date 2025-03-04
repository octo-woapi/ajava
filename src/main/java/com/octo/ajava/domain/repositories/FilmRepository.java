package com.octo.ajava.domain.repositories;

import com.octo.ajava.domain.Film;
import java.util.List;
import java.util.Optional;

public interface FilmRepository {

  List<Film> recupererLesFilms();

  List<Film> chercherDesFilms(String query);

  Optional<Film> chercherUnFilmParId(int id);
}
