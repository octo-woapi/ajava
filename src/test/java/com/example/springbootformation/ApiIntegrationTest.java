package com.example.springbootformation;

import static com.example.springbootformation.SpringbootformationApplication.FORMATEUR_DATE;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.springbootformation.infra.security.WebSecurityConfiguration;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

@Import(WebSecurityConfiguration.class)
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

  protected void laRouteEstProtegee(MockHttpServletRequestBuilder route) throws Exception {
    mockMvc.perform(route).andExpect(status().isUnauthorized());
  }
}
