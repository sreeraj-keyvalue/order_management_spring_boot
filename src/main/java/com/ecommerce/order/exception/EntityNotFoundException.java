package com.ecommerce.order.exception;

import org.springframework.http.HttpStatus;

public class EntityNotFoundException extends CommonException {

  private static final long serialVersionUID = 1L;

  public <T> EntityNotFoundException(Class<T> entity) {
    super(HttpStatus.NOT_FOUND, ExceptionCodes.ENTITY_NOT_FOUND,
        entity.getSimpleName() + " not found");
  }

}
