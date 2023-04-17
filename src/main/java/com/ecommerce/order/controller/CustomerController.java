package com.ecommerce.order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.order.dto.CreateCustomerDto;
import com.ecommerce.order.dto.UpdateCustomerDto;
import com.ecommerce.order.exception.EntityNotFoundException;
import com.ecommerce.order.model.Customer;
import com.ecommerce.order.service.CustomerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("/")
    public List<Customer> findAll() {
        return customerService.findAll();
    }

    @GetMapping("/{id}")
    public Customer findOne(@PathVariable Integer id) throws EntityNotFoundException {
        return customerService.findOne(id);
    }

    @PostMapping("/")
    public Customer create(@Valid @RequestBody CreateCustomerDto customerDto) {
        return customerService.create(customerDto);
    }

    @PutMapping("/{id}")
    public Customer update(@PathVariable Integer id, @Valid @RequestBody UpdateCustomerDto customerDto)
            throws EntityNotFoundException {
        return customerService.update(id, customerDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) throws EntityNotFoundException {
        customerService.delete(id);
    }
}
