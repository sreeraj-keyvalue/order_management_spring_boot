package com.ecommerce.order.exception.dto;

import lombok.Data;

@Data
public class ErrorResponse {
    private final int statusCode;
    private final String errorCode;
    private final String message;
}
