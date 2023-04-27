package com.ecommerce.order.exception.dto;

import java.util.Date;

import lombok.Data;

@Data
public class ErrorResponse<T> {
    private final int statusCode;
    private final String errorCode;
    private final T message;
    private final String description;
    private final Date timestamp;
}
