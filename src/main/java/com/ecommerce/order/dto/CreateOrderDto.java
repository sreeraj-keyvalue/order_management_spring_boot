package com.ecommerce.order.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CreateOrderDto {
  @NotNull
  private Integer customerId;

  @NotEmpty
  @Valid
  private List<CreateOrderItemDto> orderItems;

}
