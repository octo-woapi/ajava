package com.octo.ajava.infra.controllers;

import com.octo.ajava.domain.exceptions.FilmNotFoundException;
import com.octo.ajava.domain.usecases.ChercherDesFilmsUseCase;
import com.octo.ajava.domain.usecases.ChercherUnFilmParIdUseCase;
import com.octo.ajava.domain.usecases.RecupererLesFilmsUseCase;
import com.octo.ajava.infra.configuration.security.WebSecurityConfiguration;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@Import(WebSecurityConfiguration.class)
@WebMvcTest(FilmController.class)
class FilmControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ChercherUnFilmParIdUseCase chercherUnFilmParIdUseCase;

    @MockBean
    private ChercherDesFilmsUseCase chercherDesFilmsUseCase;

    @MockBean
    private RecupererLesFilmsUseCase recupererLesFilmsUseCase;

    @Test
    public void doit_renvoyer_une_erreur_404_quand_le_film_demande_nest_pas_trouve() throws Exception {
        // Given
        String bodyAttendu = "{\"message\":\"La ressource demandée 1 n'a pas été trouvée\"}";
        when(chercherUnFilmParIdUseCase.executer("1")).thenThrow(new FilmNotFoundException("1"));

        // When
        MockHttpServletResponse response = mockMvc
                .perform(MockMvcRequestBuilders.get("/api/films/1"))
                .andReturn()
                .getResponse();

        // Then
        assertThat(response.getStatus()).isEqualTo(NOT_FOUND.value());
        assertThat(response.getContentAsByteArray()).asString().isEqualTo(bodyAttendu);

    }
}
