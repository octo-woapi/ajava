package com.octo.ajava.domain.usecases;

import com.octo.ajava.domain.Film;
import static com.octo.ajava.fixture.FilmFixture.deuxFilmsPopulaires;
import com.octo.ajava.infra.repositories.InMemoryFilmRepository;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.BDDMockito.given;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class RecupererLesFilmsUseCaseUTest {

  @InjectMocks private RecupererLesFilmsUseCase recupererLesFilmsUseCase;
  @Mock private InMemoryFilmRepository inMemoryFilmRepository;

  @Test
  void executer_devrait_renvoyer_une_liste_de_films_in_memory() {
    // Given
    List<Film> filmsAttendus = deuxFilmsPopulaires();
    given(inMemoryFilmRepository.recupererLesFilms()).willReturn(filmsAttendus);

    // When
    List<Film> filmsRenvoyes = recupererLesFilmsUseCase.executer();

    // Then
    assertThat(filmsRenvoyes).isNotEmpty();
    assertThat(filmsRenvoyes).isEqualTo(filmsAttendus);
  }
}
