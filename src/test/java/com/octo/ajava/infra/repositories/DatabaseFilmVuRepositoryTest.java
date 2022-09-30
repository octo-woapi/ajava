package com.octo.ajava.infra.repositories;

import com.octo.ajava.domain.FilmVu;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.MountableFile;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
class DatabaseFilmVuRepositoryTest {
    @Inject
    private DatabaseFilmVuRepository databaseFilmVuRepository;

    @Container
    static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:14-alpine")
            .withCopyFileToContainer(
                    MountableFile.forClasspathResource("/docker_postgres_init.sql"),
                    "/docker-entrypoint-initdb.d/");

    @DynamicPropertySource
    static void registerMySQLProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
        registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
    }

    @Test
    public void doit_ajouter_un_utilisateur_et_retourner_le_film_ajoute_en_base() {
        //given
        var filmVu = new FilmVu(1, "Basic dXNlcjpwYXNzd29yZA==", "10/10", "Batman c'est ouf");
        // when
        var result = databaseFilmVuRepository.ajouterUnFilmVu(filmVu);
        // then
        assertThat(result).isEqualTo(filmVu);
    }

    @Test
    public void doit_retourner_la_liste_des_films_par_utilisateur() {
        // given
        var unPremierFilmVu = new FilmVu(1, "Basic dXNlcjpwYXNzd29yZA==", "10/10", "Batman c'est ouf");
        var unSecondFilmVu = new FilmVu(2, "Basic dXNlcjpwYXNzd29yZA==", "1/10", "Star nul");
        databaseFilmVuRepository.ajouterUnFilmVu(unPremierFilmVu);
        databaseFilmVuRepository.ajouterUnFilmVu(unSecondFilmVu);

        // when
        var result = databaseFilmVuRepository.recupererMesFilmsVus("Basic dXNlcjpwYXNzd29yZA==");

        // then
        assertThat(result.size()).isEqualTo(2);
    }
}
