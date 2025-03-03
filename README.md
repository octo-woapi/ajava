# ☕ AJAVA

> Ce repository contient le code source du TP sur le développement d'API en JAVA/Spring.

## 💻 Installation

Vous devez avoir sur votre poste :

* JAVA en version 17
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

L'application démarre sur le port par défaut de Spring-Boot, **8080**.

### La base de données

Pour démarrer la BDD, il faut démarrer un container avec la commande :

``` shell
docker-compose up -d
```

La BDD démarre sur le port **15432**.

Les informations de la BDD comme le login, le password, le port, la bddname sont disponibles dans le
fichier `docker-compose.yml`

### Accès à l'API avec Swagger-UI

Ouvrir la page `http://localhost:8080/swagger-ui/index.html`

![Swagger UI](doc/img/swagger.png)

## ✅ Tests

Pour lancer tous les tests :

* sur linux / macos
    ```shell
    ./gradlew test
    ```
* sur windows
    ```shell
    ./gradlew.bat test
    ```

---

# TP 5 - Ajouter un film vu

## Objectif

Ajouter un film vu en BDD, provenant de l'API TMDB, avec une note et un commentaire.

* Lancer la commande `docker compose up -d` pour lancer le postgres
* Compléter le controller `FilmVuController`
    * Utilisation de la classe `Authentication` en paramètre de la fonction du endpoint pour récupérer l'information de l'utilisateur connecté
    * Utilisation de la classe `FilmVuAAjouterApi` en paramètre de la fonction du endpoint pour récupérer le body de la request avec l'annotation `@RequestBody
* Compléter le use case `AjouterUnFilmVuUseCase`
    * En utilisant le `filmVuRepository`
* Compléter le test `DatabaseFilmVuRepositoryFTest`
* Compléter le test `AjouterUnFilmVuUseCaseTest`
* Compléter le test `FilmVuControllerFTest`
* Vous pourrez aussi avant de démarrer le swagger pour tester l'application corriger ou écrire les tests existants pour chaques classes

---

<details>
  <summary>Documentations officielles</summary>

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
