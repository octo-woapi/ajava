# ☕ AJAVA

> Ce repository contient le code source du TP sur le développement d'API en JAVA/Spring.

## 💻 Installation

Vous devez avoir sur votre poste :

* JAVA en version 21
* DOCKER (et docker-compose)

## 🚀 Démarrage

### Application Spring-Boot

Pour démarrer l'application, jouer la commande suivante :

* sur linux / macos :
    ```shell
    ./gradlew bootRun
    ```
* sur windows :
    ```shell
    ./gradlew.bat bootRun
    ```

L'application démarre sur le port par défaut de Spring-Boot, **8080**. <br>
Et swagger se trouve ici : http://localhost:8080/swagger-ui/index.html

### La base de données

Pour démarrer la BDD, il faut démarrer un container avec la commande :

``` shell
docker-compose up -d
```

La BDD démarre sur le port **15432**.

Les informations de la BDD comme le login, le password, le port, la bddname sont disponibles dans le
fichier `docker-compose.yml`

## Prerequis pour les tps

Deux choix possibles :
- partir des branches **tp-X_solution** à chaque fin de tp
- partir de la branche **tp-1_lister_des_films** et continuer tout au long des tps à partir de cette branche

Si vous partez de la branche **tp-1_lister_des_films** pour les tps, pour le tp 4 vous aurez besoin de :
- cette [classe de configuration](https://github.com/octo-woapi/AJAVA/blob/tp-4_solution_avec_basic_auth/src/main/java/com/octo/ajava/infra/configuration/security/WebSecurityConfiguration.java) (à mettre au même endroit dans l'arborescence)
- et de décommenter dans le `build.gradle` les lignes 40 à 42 puis de lancer un `./gradlew build` à la racine du projet dans ou via intellij

---

## Prerequis pour l'API TMDB

- Doc pour le tp 2 : https://developer.themoviedb.org/reference/movie-popular-list
- Doc pour le tp 3 : https://developer.themoviedb.org/reference/search-movie
- Doc pour le tp 7 : https://developer.themoviedb.org/reference/movie-details

# TP 1 - Lister des Films

* Objectif : Exposer la liste des films sur la route `http://localhost:8080/api/films`
    * Compléter le usecase `RecupererLesFilmsUseCase`, afin qu'il renvoie la liste de films "in memory"
      * Il existe une classe avec des `InMemoryFilmRepository` avec des fausses donneés dedans vous pouvez vous en servir
    * Se servir de l'Injection de Dépendence pour utiliser un `FilmRepository`
* Vous pourrez aussi avant de démarrer le swagger pour tester l'application corriger ou écrire les tests existants pour chaques classes

---

<details>
  <summary>Documentations officielles Spring</summary>

### Documentations Spring

* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.7.1/gradle-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.7.1/gradle-plugin/reference/html/#build-image)
* [Testcontainers Postgres Module Reference Guide](https://www.testcontainers.org/modules/databases/postgres/)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/2.7.1/reference/htmlsingle/#using.devtools)
* [Spring Security](https://docs.spring.io/spring-boot/docs/2.7.1/reference/htmlsingle/#web.security)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.7.1/reference/htmlsingle/#web)
* [Testcontainers](https://www.testcontainers.org/)
* [Spring REST Docs](https://docs.spring.io/spring-restdocs/docs/current/reference/html5/)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/2.7.1/reference/htmlsingle/#data.sql.jpa-and-spring-data)
* [Spring Boot Actuator](https://docs.spring.io/spring-boot/docs/2.7.1/reference/htmlsingle/#actuator)

### Guides

* [Securing a Web Application](https://spring.io/guides/gs/securing-web/)
* [Spring Boot and OAuth2](https://spring.io/guides/tutorials/spring-boot-oauth2/)
* [Authenticating a User with LDAP](https://spring.io/guides/gs/authenticating-ldap/)
* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Building a RESTful Web Service with Spring Boot Actuator](https://spring.io/guides/gs/actuator-service/)

### Liens supplémentaires

* [Gradle Build Scans – insights for your project's build](https://scans.gradle.com#gradle)

</details>
