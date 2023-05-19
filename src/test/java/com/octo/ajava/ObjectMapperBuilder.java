package com.octo.ajava;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import static com.octo.ajava.ApiIntegrationTest.FORMATEUR_DATE;

public class ObjectMapperBuilder {

    public static ObjectMapper handle() {
        var objectMapper = new ObjectMapper();
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(new LocalDateSerializer(FORMATEUR_DATE));
        objectMapper.registerModule(javaTimeModule);

        return objectMapper;
    }
}
