package com.example.springbootformation;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.SignStyle;
import java.time.temporal.ChronoField;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootformationApplication {

  public static final DateTimeFormatter FORMATEUR_DATE =
      (new DateTimeFormatterBuilder())
          .appendValue(ChronoField.DAY_OF_MONTH, 2)
          .appendLiteral('/')
          .appendValue(ChronoField.MONTH_OF_YEAR, 2)
          .appendLiteral('/')
          .appendValue(ChronoField.YEAR_OF_ERA, 4, 10, SignStyle.EXCEEDS_PAD)
          .toFormatter();

  public static void main(String[] args) {
    SpringApplication.run(SpringbootformationApplication.class, args);
  }
}
