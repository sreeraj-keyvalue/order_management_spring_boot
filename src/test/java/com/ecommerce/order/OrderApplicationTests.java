package com.ecommerce.order;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.ecommerce.order.controller.OrderController;
import com.ecommerce.order.repository.OrderRepository;
import com.ecommerce.order.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class OrderApplicationTests {

  @Autowired
  ApplicationContext ctx;

  @Test
  void contextLoads() {
    OrderController orderController = ctx.getBean(OrderController.class);
    assertNotNull(orderController);

    OrderService orderService = ctx.getBean(OrderService.class);
    assertNotNull(orderService);

    OrderRepository orderRepository = ctx.getBean(OrderRepository.class);
    assertNotNull(orderRepository);
  }
}


