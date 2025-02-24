package com.octo.ajava;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.SignStyle;
import java.time.temporal.ChronoField;

public class ObjectMapperBuilder {

  public static final DateTimeFormatter FORMATEUR_DATE =
      (new DateTimeFormatterBuilder())
          .appendValue(ChronoField.DAY_OF_MONTH, 2)
          .appendLiteral('/')
          .appendValue(ChronoField.MONTH_OF_YEAR, 2)
          .appendLiteral('/')
          .appendValue(ChronoField.YEAR_OF_ERA, 4, 10, SignStyle.EXCEEDS_PAD)
          .toFormatter();

  public static ObjectMapper handle() {
    var objectMapper = new ObjectMapper();
    JavaTimeModule javaTimeModule = new JavaTimeModule();
    javaTimeModule.addSerializer(new LocalDateSerializer(FORMATEUR_DATE));
    objectMapper.registerModule(javaTimeModule);

    return objectMapper;
  }
}
