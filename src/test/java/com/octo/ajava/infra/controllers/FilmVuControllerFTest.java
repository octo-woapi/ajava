package com.octo.ajava.infra.controllers;

import static org.assertj.core.api.Assertions.assertThat;

import com.octo.ajava.AjavaApplication;
import com.octo.ajava.ObjectMapperBuilder;
import com.octo.ajava.domain.FilmVu;
import com.octo.ajava.infra.repositories.DatabaseFilmVuDAO;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
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
  @Autowired DatabaseFilmVuDAO databaseFilmVuDAO;

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
  }

  @Test
  void ajouterFilmVu_devrait_renvoyer_201_avec_le_film_vu() throws Exception {
    // When
    var response =
        RestAssured.given()
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
            .statusCode(HttpStatus.CREATED.value())
            .extract()
            .response()
            .asString();

    // Then
    FilmVu filmAjoute = ObjectMapperBuilder.handle().readValue(response, FilmVu.class);

    assertThat(filmAjoute.getFilmId()).isEqualTo(1);
    assertThat(filmAjoute.getUtilisateurId()).isEqualTo("user");
  }

  @DisplayName("devrait modifier la note et le commentaire déjà existant")
  @Test
  void modifierNoteEtCommentaire() {
    // Given
    var filmVu = databaseFilmVuDAO.save(new FilmVu(1, "user", "10/10", "Batman c'est ouf"));

    // When
    var response =
        RestAssured.given()
            .contentType("application/json")
            .auth()
            .basic("user", "password")
            .body(
                """
                    {
                      "filmId": 1,
                      "note": "05/10",
                      "commentaire": "Batman c'est pas ouf"
                    }
                    """)
            .put(TODO)
            .then()
            .statusCode(HttpStatus.OK.value());

    // Then
    FilmVu filmVuModifie = databaseFilmVuDAO.findById(TODO);
    assertThat(TODO).isEqualTo("05/10");
    assertThat(TODO).isEqualTo("Batman c'est pas ouf");
  }
}
