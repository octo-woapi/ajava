package com.octo.ajava.infra.repositories;

import com.octo.ajava.domain.FilmVu;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DatabaseFilmDAO extends CrudRepository<FilmVu, Long> {}
