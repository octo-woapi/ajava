package com.example.springbootformation.controller;

import com.example.springbootformation.domain.Film;
import com.example.springbootformation.service.FilmService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(FilmController.class)
@TestPropertySource(locations="classpath:test.properties")
public class FilmControllerTest {

    @MockBean
    FilmService filmService;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void shouldReturnAListOfFilm() throws Exception {

        when(filmService.list()).thenReturn(List.of(new Film("dc2e6bd1-8156-4886-adff-b39e6043af0c", "Spirited Away", "Spirited Away is an Oscar winning Japanese animated film about a ten year old girl who wanders away from her parents along a path that leads to a world ruled by strange and unusual monster-like animals. Her parents have been changed into pigs along with others inside a bathhouse full of these creatures. Will she ever see the world how it once was?")));

        var response = """
                [{"id":"dc2e6bd1-8156-4886-adff-b39e6043af0c","nom":"Spirited Away","synopsis":"Spirited Away is an Oscar winning Japanese animated film about a ten year old girl who wanders away from her parents along a path that leads to a world ruled by strange and unusual monster-like animals. Her parents have been changed into pigs along with others inside a bathhouse full of these creatures. Will she ever see the world how it once was?"}]
                """;

        mockMvc.perform(get("/films"))
                .andExpect(status().isOk())
                .andExpect(content().json(response));
    }
}
