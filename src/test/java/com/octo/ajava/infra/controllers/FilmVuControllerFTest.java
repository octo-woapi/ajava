package com.octo.ajava.infra.controllers;

import com.octo.ajava.AjavaApplication;
import com.octo.ajava.ObjectMapperBuilder;
import com.octo.ajava.domain.FilmVu;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
        classes = AjavaApplication.class
)
class FilmVuControllerFTest {

  @Test
  void ajouterFilmVu_devrait_renvoyer_201_avec_le_film_vu() throws Exception {
    // Given

    // When
    var response = RestAssured.given()
            .contentType("application/json")
            .auth().basic("user", "password")
            .body("""
                    {
                      "filmId": TODO,
                      "note": TODO,
                      "commentaire": TODO
                    }
                    """
            )
            .post("ma route que je souhaite testé")
            .then()
            .statusCode(// HttpStatus que je souhaite testé)
            .extract().response().asString();

    // Then
    FilmVu filmAjoute = ObjectMapperBuilder.handle().readValue(response, FilmVu.class);

    // assertion
  }

}
