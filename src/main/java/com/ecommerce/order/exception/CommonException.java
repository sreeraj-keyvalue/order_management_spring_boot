package com.ecommerce.order.exception;

import org.springframework.http.HttpStatusCode;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommonException extends RuntimeException {
    private HttpStatusCode statusCode;
    private String errorCode;
    private String message;
}