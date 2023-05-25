package com.octo.ajava.infra.controllers;

import com.octo.ajava.AjavaApplication;
import com.octo.ajava.ObjectMapperBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import io.restassured.RestAssured;
import com.octo.ajava.domain.Film;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
        classes = AjavaApplication.class
)
class FilmControllerFTest {

    @Test
    void recuperTousLesFilms_devrait_renvoyer_une_HTTP_200_et_une_liste_de_film() throws Exception {
        // Given

        // When
        var response = RestAssured.given()
                .get("la route que je souhaite tester")
                .then()
                .statusCode(HttpStatus.OK.value())
                .extract().response().asString();

        // Then
        var listeDeFilms = ObjectMapperBuilder.handle().readValue(response, Film[].class);

        Assertions.assertEquals(0, listeDeFilms.length);
    }
}