package com.octo.ajava;

import static java.time.format.SignStyle.EXCEEDS_PAD;
import static java.time.temporal.ChronoField.DAY_OF_MONTH;
import static java.time.temporal.ChronoField.MONTH_OF_YEAR;
import static java.time.temporal.ChronoField.YEAR_OF_ERA;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

public class ObjectMapperBuilder {

  public static final DateTimeFormatter FORMATEUR_DATE =
      new DateTimeFormatterBuilder()
          .appendValue(DAY_OF_MONTH, 2)
          .appendLiteral('/')
          .appendValue(MONTH_OF_YEAR, 2)
          .appendLiteral('/')
          .appendValue(YEAR_OF_ERA, 4, 10, EXCEEDS_PAD)
          .toFormatter();

  public static ObjectMapper handle() {
    JavaTimeModule javaTimeModule = new JavaTimeModule();
    javaTimeModule.addSerializer(new LocalDateSerializer(FORMATEUR_DATE));
    return new ObjectMapper().registerModule(javaTimeModule);
  }
}
