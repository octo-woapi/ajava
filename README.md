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

---

# TP 4 - Utiliser OAUTH 2

## Prérequis pour OAuth 2

### Installation et utilisation de Keyclock

* Utilisation de l'image docker Keycloak 19.0.2 : https://quay.io/repository/keycloak/keycloak?tab=tags
* Modification du fichier `docker-compose.yml` et configuration des **utilisateurs** avec le
  fichier `keycloak/realms/realm.json`
  ![docker-compose](doc/img/docker-compose-keycloak.png)

#### :frowning_person: :policewoman: Utilisateur Keycloak déjà configuré

| Authentification | username   | Password | UserId  | Roles |
|------------------|------------|----------|---------|-------|
| OAuth 2.0        | jeandurant | password | jdurant | USER  |

#### :key: Utilisation d'OAuth 2.0

* [Documentation sur la gestion d'OAuth 2.0 et du JWT Token](https://docs.spring.io/spring-security/reference/reactive/oauth2/resource-server/jwt.html)
* Authentification sur Swagger-UI et sur la page de login Keycloak

<p float="left">
<img src="doc/img/oauth2-swagger.png" width="400" />
<img src="doc/img/keycloak-sign-in.png" width="400" />
</p>

## Prérequis pour l'API TMDB

* Obtenir un compte pour utiliser l'API TMDB : [Page d'inscription](https://www.themoviedb.org/signup)
* Après l'inscription, votre Jeton d'accès en lecture à l'API va être généré : https://www.themoviedb.org/settings/api
* <img src="doc/img/token-api-TMDB.png" width="700" />
* Créer un fichier `src/main/resources/.env`, avec le même contenu que le fichier `.env.exemple`
* Remplacer `<METTRE ICI LE JETON TMDB>` par votre **Jeton d'accès** à l'API TMDB

## Objectif

Pouvoir utiliser l'api (ex. via Swagger-UI) en s'étant authentifier au préalable, via Keycloak.

* Compléter le usecase `RecupererLesFilmsUseCase`, afin qu'il renvoie la liste de films "TMDB"
* Se servir de l'Injection de Dépendance pour utiliser un `FilmRepository` "TMDB'
* Utilisation d'une instance `TMDBHttpClient` pour appeler
  l'[API TMDB](https://developers.themoviedb.org/3/movies/get-popular-movies)
* Utilisation d'une instance `TMDBFilmMapper` pour effectuer la conversion de **TMDB Movies** en `Films`

## Démarrer les différents containers Docker

```ssh
docker-compose up -d
```

![docker-compose up -d](doc/img/docker-compose_up.png)

## Résultat avec Swagger-UI

![TMDB movies](doc/img/swagger-tmdb-movies.png)

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
