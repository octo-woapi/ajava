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

Les informations de la BDD comme le login, le password, le port, la bddname sont disponibles dans le fichier `docker-compose.yml`
