package com.ecommerce.order.model;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class OrderItem extends BaseEntity {

    @Nonnull
    @ManyToOne
    @JoinColumn
    private Order order;

    @Nonnull
    @ManyToOne
    @JoinColumn
    private Product product;

    @Nonnull
    @Min(1)
    private Integer quantity;
}
