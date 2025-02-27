package com.octo.ajava.domain.usecases;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import com.octo.ajava.domain.FilmVu;
import com.octo.ajava.domain.repositories.FilmVuRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ModifierUnFilmVuUseCaseTest {

  @Mock private FilmVuRepository filmVuRepository;
  @InjectMocks private ModifierUnFilmVuUseCase modifierUnFilmVuUseCase;

  @DisplayName("devrait modifier un FilmVu déjà existant en appelant le repository")
  @Test
  void modifierUnFilmVu() throws Exception {
    // Given
    FilmVu filmVu = new FilmVu(12, "user", "10/10", "Batman c'est ouf");
    given(filmVuRepository.modifierUnFilmVu(any())).willReturn(null); // TODO

    // When
    modifierUnFilmVuUseCase.executer(filmVu); // TODO

    // Then
    verify(filmVuRepository).modifierUnFilmVu(filmVu);
  }
}
