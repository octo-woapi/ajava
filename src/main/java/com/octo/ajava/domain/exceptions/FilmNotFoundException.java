package com.octo.ajava.domain.exceptions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FilmNotFoundException extends NotFoundException {

  public FilmNotFoundException(String resource) {
    super(resource);
    Logger logger = LogManager.getLogger(FilmNotFoundException.class);
    logger.warn("Le film demandé " + resource + " n'a pas été");
  }
}
