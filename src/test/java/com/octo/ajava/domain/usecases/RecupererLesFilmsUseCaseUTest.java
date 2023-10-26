package com.octo.ajava.domain.usecases;

import static com.octo.ajava.fixture.FilmFixture.deuxFilmsPopulaires;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import com.octo.ajava.domain.Film;
import com.octo.ajava.infra.repositories.InMemoryFilmRepository;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
