package com.octo.ajava.domain.usecases;

import static com.octo.ajava.fixtures.FilmTestFixture.unFilm;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import com.octo.ajava.domain.Film;
import com.octo.ajava.domain.repositories.FilmRepository;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ChercherDesFilmsUseCaseTest {

  @Mock private FilmRepository filmRepository;
  @InjectMocks private ChercherDesFilmsUseCase chercherDesFilmsUseCase;

  @Test
  void devrait_retourner_la_liste_des_films() throws Exception {
    // Given
    String titreRecherche = "Pulp Fiction";
    Film unFilm = unFilm().avecTitre(titreRecherche).build();

    given(filmRepository.chercherDesFilms(any())).willReturn(List.of(unFilm));

    // When
    List<Film> result = chercherDesFilmsUseCase.executer(titreRecherche);

    // Then
    verify(filmRepository).chercherDesFilms(titreRecherche);
    assertThat(result).hasSize(1);
  }
}
