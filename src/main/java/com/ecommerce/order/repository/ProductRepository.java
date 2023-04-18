package com.ecommerce.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.order.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
