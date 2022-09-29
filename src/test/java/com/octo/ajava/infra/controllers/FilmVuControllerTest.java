package com.octo.ajava.infra.controllers;

import com.octo.ajava.ApiIntegrationTest;
import com.octo.ajava.domain.FilmVu;
import com.octo.ajava.domain.usecases.RecupererMesFilmsVusUseCase;
import com.octo.ajava.fixture.FilmVuFixture;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletResponse;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(FilmVuController.class)
class FilmVuControllerTest extends ApiIntegrationTest {

    @MockBean
    private RecupererMesFilmsVusUseCase recupererMesFilmsVusUseCaseMocked;

    private final List<FilmVu> expectedFilmVus = FilmVuFixture.uneListeDeFilmVus();

    @Test
    public void doit_renvoyer_le_code_http_200_quand_lister_des_films_a_renvoye_des_resultats()
        throws Exception {
        // given
        when(recupererMesFilmsVusUseCaseMocked.executer("Basic dXNlcjpwYXNzd29yZA==")).thenReturn(expectedFilmVus);

        // when
        MockHttpServletResponse response =
            mockMvc.perform(
                get("/api/film_vus")
                    .contentType(APPLICATION_JSON)
                    .with(httpBasic("user", "password"))
                ).andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(OK.value());
        assertThat(response.getContentType()).isEqualTo("application/json");
    }

}