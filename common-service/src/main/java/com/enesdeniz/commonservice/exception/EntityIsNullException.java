package com.enesdeniz.commonservice.exception;

public class EntityIsNullException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public EntityIsNullException(String msg) {
    super(msg);
  }
}
