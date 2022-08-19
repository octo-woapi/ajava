package com.example.springbootformation.infra.controller;

import com.example.springbootformation.infra.controller.FilmController;
import com.example.springbootformation.usecase.ListFilmUseCase;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations="classpath:test.properties")
public class FilmControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void shouldReturnAListOfFilm() throws Exception {

        var response = """
                [{"id":"dc2e6bd1-8156-4886-adff-b39e6043af0c","nom":"Spirited Away","synopsis":"Spirited Away is an Oscar winning Japanese animated film about a ten year old girl who wanders away from her parents along a path that leads to a world ruled by strange and unusual monster-like animals. Her parents have been changed into pigs along with others inside a bathhouse full of these creatures. Will she ever see the world how it once was?"}]
                """;

        mockMvc.perform(get("/films"))
                .andExpect(status().isOk())
                .andExpect(content().json(response));
    }
}
