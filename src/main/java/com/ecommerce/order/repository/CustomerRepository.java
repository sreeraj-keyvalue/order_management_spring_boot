package com.ecommerce.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.order.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
