package com.octo.ajava.domain.exceptions;

public class NotFoundException extends RuntimeException {

  private String resource;

  NotFoundException(String resource) {
    this.resource = resource;
  }

  public String getResource() {
    return resource;
  }
}
