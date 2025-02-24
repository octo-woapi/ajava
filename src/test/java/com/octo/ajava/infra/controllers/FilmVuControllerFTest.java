package com.octo.ajava.infra.controllers;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

import com.octo.ajava.AjavaApplication;
import com.octo.ajava.ObjectMapperBuilder;
import com.octo.ajava.domain.FilmVu;
import com.octo.ajava.infra.repositories.DatabaseFilmVuDAO;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.MountableFile;

@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    classes = AjavaApplication.class)
@Testcontainers
class FilmVuControllerFTest {

  @LocalServerPort private Integer port;
  @Autowired private DatabaseFilmVuDAO databaseFilmVuDAO;

  @Container
  static PostgreSQLContainer<?> postgreSQLContainer =
      new PostgreSQLContainer<>("postgres:14-alpine")
          .withCopyFileToContainer(
              MountableFile.forClasspathResource("/docker_postgres_init.sql"),
              "/docker-entrypoint-initdb.d/");

  @DynamicPropertySource
  static void registerMySQLProperties(DynamicPropertyRegistry registry) {
    registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
    registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
    registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
  }

  @BeforeEach
  void setUp() {
    RestAssured.port = port;
    databaseFilmVuDAO.deleteAll();
  }

  @DisplayName("devrait chercher un FilmVu et le renvoyer avec un HTTP 200")
  @Test
  void chercherUnFilmVu() throws Exception {
    // Given
    FilmVu filmVu =
        databaseFilmVuDAO.save(new FilmVu(25, "jdurant", "10/10", "Shakespeare for ever!"));

    // When
    Response response =
        given()
            .contentType("application/json")
            .auth()
            .basic("jdurant", "password")
            .get("/api/films_vus/25")
            .then()
            .statusCode(OK.value())
            .extract()
            .response();

    // Then
    FilmVu filmVuTrouve = ObjectMapperBuilder.handle().readValue(response.asString(), FilmVu.class);
    assertThat(filmVuTrouve).isEqualTo(filmVu);
  }

  @Test
  void ajouterFilmVu_devrait_renvoyer_201_avec_le_film_vu() throws Exception {
    // When
    Response response =
        given()
            .contentType("application/json")
            .auth()
            .basic("user", "password")
            .body(
                """
                    {
                      "filmId": 1,
                      "note": "10/10",
                      "commentaire": "Batman c'est ouf"
                    }
                    """)
            .post("/api/films_vus")
            .then()
            .statusCode(CREATED.value())
            .extract()
            .response();

    // Then
    FilmVu filmAjoute = ObjectMapperBuilder.handle().readValue(response.asString(), FilmVu.class);
    assertThat(filmAjoute.getFilmId()).isEqualTo(1);
    assertThat(filmAjoute.getUtilisateurId()).isEqualTo("user");
  }

  @DisplayName(
      "devrait modifier la note et le commentaire d'un FilmVu déjà existant, et renvoyer HTTP 204")
  @Test
  void modifierNoteEtCommentaire() {
    // Given
    databaseFilmVuDAO.save(new FilmVu(12, "user", "10/10", "Batman c'est ouf"));

    // When
    given()
        .contentType("application/json")
        .auth()
        .basic("user", "password")
        .body(
            """
                    {
                      "filmId": 12,
                      "note": "05/10",
                      "commentaire": "Batman c'est pas ouf"
                    }
                    """)
        .put("TODO") // TODO
        .then()
        .statusCode(OK.value()); // TODO

    // Then
    FilmVu filmVuTrouve = databaseFilmVuDAO.findByFilmIdAndUtilisateurId(12, "user");
    assertThat("TODO").isEqualTo("05/10"); // TODO
    assertThat("TODO").isEqualTo("Batman c'est pas ouf"); // TODO
  }
}
