package com.octo.ajava.infra.repositories;

import com.octo.ajava.domain.FilmVu;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DatabaseFilmVuRepositoryTest {

    @Mock
    private DatabaseFilmDAO databaseFilmDAO;

    @InjectMocks
    private DatabaseFilmVuRepository databaseFilmVuRepository;

    @Test
    public void doit_ajouter_un_utilisateur_et_retourner_le_film_ajoute_en_base() {
        var filmVu = new FilmVu(1, "Basic dXNlcjpwYXNzd29yZA==", "10/10", "Batman c'est ouf");
        when(databaseFilmDAO.save(filmVu)).thenReturn(filmVu);

        var result = databaseFilmVuRepository.ajouterUnFilmVu(filmVu);

        assertThat(result).isEqualTo(filmVu);
    }

    @Test
    public void doit_retourner_la_liste_des_films_par_utilisateur() {
        var utilisateurId = "Basic dXNlcjpwYXNzd29yZA==";
        when(databaseFilmDAO.findAllByUtilisateurId(utilisateurId)).thenReturn(List.of(
            new FilmVu(1, "Basic dXNlcjpwYXNzd29yZA==", "10/10", "Batman c'est ouf"),
            new FilmVu(2, "Basic dXNlcjpwYXNzd29yZA==", "1/10", "Star nul")
        ));

        var result = databaseFilmVuRepository.recupererMesFilmsVus(utilisateurId);

        assertThat(result.size()).isEqualTo(2);
    }

}