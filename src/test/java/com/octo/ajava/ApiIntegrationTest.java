package com.octo.ajava;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.SignStyle;
import java.time.temporal.ChronoField;

public abstract class ApiIntegrationTest {

  public static final DateTimeFormatter FORMATEUR_DATE =
          (new DateTimeFormatterBuilder())
                  .appendValue(ChronoField.DAY_OF_MONTH, 2)
                  .appendLiteral('/')
                  .appendValue(ChronoField.MONTH_OF_YEAR, 2)
                  .appendLiteral('/')
                  .appendValue(ChronoField.YEAR_OF_ERA, 4, 10, SignStyle.EXCEEDS_PAD)
                  .toFormatter();

  @Autowired protected MockMvc mockMvc;

  protected ObjectMapper objectMapper;
  protected ObjectWriter objectWriter;

  @BeforeEach
  void setUp() {
    objectMapper = new ObjectMapper();
    JavaTimeModule javaTimeModule = new JavaTimeModule();
    javaTimeModule.addSerializer(new LocalDateSerializer(FORMATEUR_DATE));
    objectMapper.registerModule(javaTimeModule);

    objectWriter = objectMapper.writer().withDefaultPrettyPrinter();
  }
}
