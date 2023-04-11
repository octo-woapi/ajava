package com.octo.ajava;

import static com.octo.ajava.AjavaApplication.FORMATEUR_DATE;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

public abstract class ApiIntegrationTest {

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
