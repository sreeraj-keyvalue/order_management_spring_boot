package com.ecommerce.order.exception;

import com.ecommerce.order.exception.dto.ErrorResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(CommonException.class)
  @ResponseStatus(value = HttpStatus.NOT_FOUND)
  public ErrorResponse<String> handleCommonException(CommonException ex, WebRequest request) {

    return (new ErrorResponse<String>(ex.getStatusCode().value(), ex.getErrorCode(),
        ex.getMessage(), request.getDescription(false), new Date()));
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ErrorResponse<Map<String, String>> handleValidationException(
      MethodArgumentNotValidException ex, WebRequest request) {
    Map<String, String> errorMessages = new HashMap<>();
    ex.getBindingResult().getAllErrors().forEach(error -> {
      String fieldName = ((FieldError) error).getField();
      String errorMessage = error.getDefaultMessage();
      errorMessages.put(fieldName, errorMessage);
    });

    return new ErrorResponse<>(HttpStatus.BAD_REQUEST.value(), ExceptionCodes.VALIDATION_ERROR,
        errorMessages, request.getDescription(false), new Date());
  }

}
