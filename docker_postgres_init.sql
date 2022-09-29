CREATE TABLE film_vu
(
    id SERIAL PRIMARY KEY,
    film_id INTEGER,
    note VARCHAR(100),
    commentaire VARCHAR(100),
    utilisateur_id VARCHAR(100)
);