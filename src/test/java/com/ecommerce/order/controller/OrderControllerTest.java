package com.ecommerce.order.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;

import com.ecommerce.order.dto.CreateOrderDto;
import com.ecommerce.order.dto.UpdateOrderDto;
import com.ecommerce.order.model.Order;
import com.ecommerce.order.service.OrderService;
import com.ecommerce.order.util.SampleInstanceCollection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;

@SpringBootTest
class OrderControllerTest {

  @MockBean
  private OrderService orderService;

  @Autowired
  private OrderController orderController;


  @Test
  @DisplayName("Should be able to create and return an order")
  void createOrderTest() {
    CreateOrderDto createOrderDto = SampleInstanceCollection.getCreateOrderDto(1);

    Order order = SampleInstanceCollection.getDummyOrder(1);

    Mockito.when(orderService.create(createOrderDto)).thenReturn(order);
    Order expectedOrder = order;
    Order actualOrder = orderController.create(createOrderDto);

    assertEquals(expectedOrder, actualOrder);
  }

  @Test
  @DisplayName("Should return page of orders")
  void findAllTest() {
    @SuppressWarnings("unchecked")
    Page<Order> orders = Mockito.mock(Page.class);

    Mockito.when(orderService.findAll(anyInt(), anyInt())).thenReturn(orders);
    Page<Order> actualOrderPage = orderController.findAll(1, 1);
    assertEquals(orders, actualOrderPage);
  }

  @Test
  @DisplayName("Should return order with given id")
  void findOneTest() {
    Order order = SampleInstanceCollection.getDummyOrder(1);

    Mockito.when(orderService.findOne(anyInt())).thenReturn(order);
    Order expectedOrder = order;
    Order actualOrder = orderController.findOne(1);

    assertEquals(expectedOrder, actualOrder);
  }

  @Test
  @DisplayName("Should return order with updated status")
  void updateTest() {
    UpdateOrderDto orderDto = SampleInstanceCollection.getUpdateOrderDto();
    Order expectedOrder = SampleInstanceCollection.getDummyOrder(1);
    expectedOrder.setStatus(orderDto.getStatus());

    Mockito.when(orderService.update(1, orderDto)).thenReturn(expectedOrder);
    Order actualOrder = orderController.update(1, orderDto);

    assertEquals(expectedOrder, actualOrder);
  }

  @Test
  @DisplayName("Should invoke delete method of order service once")
  void deleteTest() {
    orderController.delete(1);
    Mockito.verify(orderService).delete(1);
  }

}
