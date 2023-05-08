package com.ecommerce.order.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResponseDto {
  private Object data;

  private Object error;

  private Object meta;
}
