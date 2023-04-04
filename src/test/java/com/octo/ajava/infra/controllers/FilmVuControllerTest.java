package com.octo.ajava.infra.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.octo.ajava.ApiIntegrationTest;
import com.octo.ajava.domain.FilmVu;
import com.octo.ajava.domain.usecases.AjouterUnFilmVuUseCase;
import com.octo.ajava.domain.usecases.RecupererMesFilmsVusUseCase;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

@WebMvcTest(FilmVuController.class)
class FilmVuControllerTest extends ApiIntegrationTest {

  private static final String URL_FILMS_VUS = "/api/films_vus";
  @MockBean private RecupererMesFilmsVusUseCase recupererMesFilmsVusUseCaseMocked;
  @MockBean private AjouterUnFilmVuUseCase ajouterUnFilmVuUseCaseMocked;

  @Test
  public void doit_renvoyer_le_code_http_200_quand_lister_des_films_a_renvoye_des_resultats()
      throws Exception {
    // given
    String userId = "user";
    var filmsVus =
        List.of(
            new FilmVu(1, userId, "10/10", "Batman c'est ouf"),
            new FilmVu(2, userId, "1/10", "Star nul"));
    when(recupererMesFilmsVusUseCaseMocked.executer(userId)).thenReturn(filmsVus);

    // when
    mockMvc
        .perform(
            get(URL_FILMS_VUS).contentType(APPLICATION_JSON).with(httpBasic(userId, "password")))
        .andExpect(status().isOk())
        .andExpect(
            content()
                .json(
                    """
                    [
                        {
                            "id":null,
                            "filmId":1,
                            "utilisateurId":"user",
                            "note":"10/10",
                            "commentaire":"Batman c'est ouf"
                        },
                        {
                            "id":null,
                            "filmId":2,
                            "utilisateurId":"user",
                            "note":"1/10",
                            "commentaire":"Star nul"
                        }
                    ]
                """));
  }

  @Test
  public void ajouterFilmVu_devrait_renvoyer_200_avec_le_film_vu() throws Exception {
    // given
    final String userId = "jdurant";
    final FilmVu filmVu = new FilmVu(1, userId, "10/10", "Batman c'est ouf");
    when(ajouterUnFilmVuUseCaseMocked.executer(filmVu)).thenReturn(filmVu);

    // when
    mockMvc
        .perform(
            post(URL_FILMS_VUS)
                .with(httpBasic(userId, "password"))
                .contentType(APPLICATION_JSON)
                .content(
                    """
                    {
                      "filmId": 1,
                      "note": "10/10",
                      "commentaire": "Batman c'est ouf"
                    }
                    """)
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(
            content()
                .json(
                    """
                    {
                      "id":null,
                      "filmId":1,
                      "utilisateurId":"jdurant",
                      "note":"10/10",
                      "commentaire":"Batman c'est ouf"
                    }
                    """));
  }
}