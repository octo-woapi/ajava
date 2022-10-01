CREATE TABLE film_vu
(
    id             BIGSERIAL PRIMARY KEY,
    film_id        INTEGER,
    note           VARCHAR(100),
    commentaire    VARCHAR(100),
    utilisateur_id VARCHAR(100)
);

CREATE TABLE utilisateur
(
    id       BIGSERIAL PRIMARY KEY,
    nom      VARCHAR(100),
    prenom   VARCHAR(100),
    password VARCHAR(100)
);
