package com.ecommerce.order.model;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@Entity
public class Product extends BaseEntity {

    @Nonnull
    private String name;

    @ManyToOne
    @JoinColumn
    private Category category;

    private String image;

    private String description;

    private double unitPrice;
}
