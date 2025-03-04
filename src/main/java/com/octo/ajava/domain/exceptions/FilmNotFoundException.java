package com.octo.ajava.domain.exceptions;

public class FilmNotFoundException extends NotFoundException {

  public FilmNotFoundException(int id) {
    super(String.valueOf(id));
  }
}
