package com.ecommerce.order.controller;

import com.ecommerce.order.dto.CreateOrderDto;
import com.ecommerce.order.dto.UpdateOrderDto;
import com.ecommerce.order.model.Order;
import com.ecommerce.order.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/orders/")
public class OrderController {

  @Autowired
  private OrderService orderService;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public Page<Order> findAll(@RequestParam Integer page, @RequestParam Integer size) {
    return orderService.findAll(page, size);
  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Order findOne(@PathVariable Integer id) {
    return orderService.findOne(id);
  }

  @PostMapping("/")
  @ResponseStatus(HttpStatus.CREATED)
  public Order create(@Valid @RequestBody CreateOrderDto orderDto) {
    return orderService.create(orderDto);
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.CREATED)
  public Order update(@PathVariable Integer id, @Valid @RequestBody UpdateOrderDto orderDto) {
    return orderService.update(id, orderDto);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public void delete(@PathVariable Integer id) {
    orderService.delete(id);
  }
}
