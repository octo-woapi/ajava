template de test fonctionnel avec spring boot test
```java
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
        classes = AjavaApplication.class
)
class MonControllerTest {

    @Test
    void la_route_que_je_souhaite_tester() throws Exception {
        // Given

        // When
        var response = RestAssured.given()
                .get("/la_route_que_je_souhaite_tester")
                .then()
                .statusCode(HttpStatus.OK.value())
                .extract().response().asString();

        // Then
        String responseToTest = ObjectMapperBuilder.handle().readValue(response, String.class);
        
        assertThat(listeDeFilms.length).isEqualTo(22);
    }
}
```