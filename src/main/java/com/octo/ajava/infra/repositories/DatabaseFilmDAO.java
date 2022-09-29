package com.octo.ajava.infra.repositories;

import com.octo.ajava.domain.FilmVu;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DatabaseFilmDAO extends CrudRepository<FilmVu, Long> {
    List<FilmVu> findAllByUtilisateurId(String utilisateurId);
}
