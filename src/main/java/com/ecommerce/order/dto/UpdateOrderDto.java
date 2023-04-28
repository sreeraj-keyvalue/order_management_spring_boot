package com.ecommerce.order.dto;

import com.ecommerce.order.constants.OrderStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateOrderDto {

  @NotNull
  private OrderStatus status;

}
