package com.ecommerce.order.model;

import java.util.List;

import com.ecommerce.order.constants.OrderStatus;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Order extends BaseEntity {

    @Nonnull
    @ManyToOne
    @JoinColumn
    private Customer customer;

    @Nonnull
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @OneToMany
    private List<OrderItem> orderItems;
}
