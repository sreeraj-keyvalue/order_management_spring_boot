package com.ecommerce.order.model;

import java.util.List;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Category extends BaseEntity {

    @Nonnull
    private String name;

    private Integer parentCategory;

    @OneToMany
    private List<Product> products;
}
