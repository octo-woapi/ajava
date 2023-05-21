package com.octo.ajava;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.SignStyle;
import java.time.temporal.ChronoField;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AjavaApplication {

  public static void main(String[] args) {
    SpringApplication.run(AjavaApplication.class, args);
  }
}
