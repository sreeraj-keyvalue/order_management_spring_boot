package com.ecommerce.order.dto;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateOrderDto {
    @NotNull
    private Integer customerId;

    @NotEmpty
    @Valid
    private List<CreateOrderItemDto> orderItems;
}
