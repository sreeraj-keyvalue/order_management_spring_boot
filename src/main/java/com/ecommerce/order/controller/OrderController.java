package com.ecommerce.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.order.dto.CreateOrderDto;
import com.ecommerce.order.dto.UpdateOrderDto;
import com.ecommerce.order.model.Order;
import com.ecommerce.order.service.OrderService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/")
    public Page<Order> findAll() {
        return orderService.findAll(0, 5);
    }

    @GetMapping("/{id}")
    public Order findOne(@PathVariable Integer id) {
        return orderService.findOne(id);
    }

    @PostMapping("/")
    public Order create(@Valid @RequestBody CreateOrderDto orderDto) {
        return orderService.create(orderDto);
    }

    @PutMapping("/{id}")
    public Order update(@PathVariable Integer id, @Valid @RequestBody UpdateOrderDto orderDto) {
        return orderService.update(id, orderDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        orderService.delete(id);
    }
}
