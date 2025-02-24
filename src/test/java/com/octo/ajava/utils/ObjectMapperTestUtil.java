package com.octo.ajava.utils;

import static java.time.format.SignStyle.EXCEEDS_PAD;
import static java.time.temporal.ChronoField.DAY_OF_MONTH;
import static java.time.temporal.ChronoField.MONTH_OF_YEAR;
import static java.time.temporal.ChronoField.YEAR_OF_ERA;
import static java.util.Arrays.asList;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.octo.ajava.domain.Film;
import com.octo.ajava.domain.FilmVu;
import com.octo.ajava.infra.api_client.entities.PaginatedTMDBMovies;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.List;

public class ObjectMapperTestUtil {

  private static final DateTimeFormatter FORMATEUR_DATE =
      new DateTimeFormatterBuilder()
          .appendValue(DAY_OF_MONTH, 2)
          .appendLiteral('/')
          .appendValue(MONTH_OF_YEAR, 2)
          .appendLiteral('/')
          .appendValue(YEAR_OF_ERA, 4, 10, EXCEEDS_PAD)
          .toFormatter();

  private static final ObjectMapper objectMapper;

  static {
    JavaTimeModule javaTimeModule = new JavaTimeModule();
    javaTimeModule.addSerializer(new LocalDateSerializer(FORMATEUR_DATE));
    objectMapper = new ObjectMapper().registerModule(javaTimeModule);
  }

  public static FilmVu convertirEnFilmVu(String string) throws Exception {
    return objectMapper.readValue(string, FilmVu.class);
  }

  public static PaginatedTMDBMovies convertirEnPaginatedTMDBMovies(String string) throws Exception {
    return objectMapper.readValue(string, PaginatedTMDBMovies.class);
  }

  public static List<Film> convertirEnUneListeDeFilms(String string) throws Exception {
    return asList(objectMapper.readValue(string, Film[].class));
  }
}
