package com.octo.ajava.infra.errorHandling;

import static org.springframework.core.Ordered.HIGHEST_PRECEDENCE;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import com.octo.ajava.domain.exceptions.NotFoundException;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Order(HIGHEST_PRECEDENCE)
@RestControllerAdvice(basePackages = {"com.octo.ajava"})
public class GlobalControllerAdvice {

  @ExceptionHandler(NotFoundException.class)
  @ResponseStatus(NOT_FOUND)
  public MessageErreurApi handleNotFoundException(NotFoundException exception) {
    return new MessageErreurApi(
        "La ressource demandée " + exception.getResource() + " n'a pas été trouvée");
  }
}
