package com.ecommerce.order.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatusCode;

@Getter
@AllArgsConstructor
public class CommonException extends RuntimeException {

  private final HttpStatusCode statusCode;
  private final String errorCode;
  private final String message;

}
