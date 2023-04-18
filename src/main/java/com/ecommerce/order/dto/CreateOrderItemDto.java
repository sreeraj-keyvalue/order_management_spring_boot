package com.ecommerce.order.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateOrderItemDto {

    @NotNull
    private Integer productId;

    @NotNull
    @Min(1)
    private Integer quantity;
}
