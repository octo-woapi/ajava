package com.example.springbootformation.usecase;

import com.example.springbootformation.domain.Film;
import com.example.springbootformation.domain.FilmRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ListFilmUseCaseTest {

    @Mock
    private FilmRepository filmRepository;

    @InjectMocks
    private ListFilmUseCase listFilmUseCase;

    @Test
    public void shouldReturnAListOfFilm() throws Exception {
        // Given
        when(filmRepository.list()).thenReturn(List.of(new Film("dc2e6bd1-8156-4886-adff-b39e6043af0c", "Spirited Away", "Spirited Away is an Oscar winning Japanese animated film about a ten year old girl who wanders away from her parents along a path that leads to a world ruled by strange and unusual monster-like animals. Her parents have been changed into pigs along with others inside a bathhouse full of these creatures. Will she ever see the world how it once was?")));

        // When
        var result = listFilmUseCase.handle();

        // Then
        assertThat(result.size()).isEqualTo(1);
    }

}