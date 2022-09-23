package com.example.springbootformation.infra.repositories;

import com.example.springbootformation.domain.repositories.FilmRepository;
import com.example.springbootformation.fixture.MockedFilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class FilmRepositoryFactory {

    @Autowired
    private Environment environment;

    public FilmRepository getFilmRepository() {

        if(environment.getActiveProfiles()[0].equals("test")) {
            return new MockedFilmRepository();
        } else {
            return new TheMovieDatabaseApiRepository();
        }
    }
}
