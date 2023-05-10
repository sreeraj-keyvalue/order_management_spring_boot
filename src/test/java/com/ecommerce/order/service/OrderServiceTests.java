package com.ecommerce.order.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;

import com.ecommerce.order.constants.OrderStatus;
import com.ecommerce.order.dto.CreateOrderDto;
import com.ecommerce.order.dto.UpdateOrderDto;
import com.ecommerce.order.exception.EntityNotFoundException;
import com.ecommerce.order.model.Customer;
import com.ecommerce.order.model.Order;
import com.ecommerce.order.model.OrderItem;
import com.ecommerce.order.model.Product;
import com.ecommerce.order.repository.OrderRepository;
import com.ecommerce.order.util.SampleInstanceCollection;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;


@SpringBootTest
class OrderServiceTests {
  @MockBean
  private OrderRepository orderRepository;

  @MockBean
  private CustomerService customerService;

  @MockBean
  private ProductService productService;

  @Autowired
  private OrderService orderService;

  @Test
  @DisplayName("Should be able to create an order")
  void createOrderTest() {
    final int customerId = 1;
    final int productId = 10;
    Mockito.when(customerService.findOne(customerId))
        .thenReturn(SampleInstanceCollection.getDummyCustomer(customerId));

    Mockito.when(productService.findOne(productId))
        .thenReturn(SampleInstanceCollection.getDummyProduct(productId));

    Order order = Order.builder().customer(SampleInstanceCollection.getDummyCustomer(customerId))
        .status(OrderStatus.INITIATED).build();

    List<OrderItem> orderItems = new ArrayList<OrderItem>();
    orderItems.add(SampleInstanceCollection.getDummyOrderItem(1));

    order.setOrderItems(orderItems);

    CreateOrderDto orderDto = SampleInstanceCollection.getCreateOrderDto(customerId);

    Mockito.when(orderRepository.save(any())).thenReturn(order);
    Order newOrder = orderService.create(orderDto);
    assertEquals(newOrder, order);
  }

  @Test
  @DisplayName("Should fail creating order for invalid customer")
  void failCreateOrderInvalidCustomerTest() {
    CreateOrderDto orderDto = SampleInstanceCollection.getCreateOrderDto(1);

    Mockito.when(customerService.findOne(any()))
        .thenThrow(new EntityNotFoundException(Customer.class));
    assertThrows(EntityNotFoundException.class, () -> orderService.create(orderDto));
  }

  @Test
  @DisplayName("Should fail creating order for invalid product")
  void failCreateOrderInvalidProductTest() {
    CreateOrderDto orderDto = SampleInstanceCollection.getCreateOrderDto(1);

    Mockito.when(customerService.findOne(any()))
        .thenReturn(SampleInstanceCollection.getDummyCustomer(1));
    Mockito.when(productService.findOne(any()))
        .thenThrow(new EntityNotFoundException(Product.class));
    assertThrowsExactly(EntityNotFoundException.class, () -> orderService.create(orderDto));
  }

  @Test
  @DisplayName("Should find order with given id")
  void findOneByIdTest() {
    final int orderId = 1;
    Order order = SampleInstanceCollection.getDummyOrder(orderId);

    Mockito.when(orderRepository.findById(any())).thenReturn(Optional.of(order));
    Order retrievedOrder = orderService.findOne(orderId);
    assertEquals(retrievedOrder, order);
  }

  @Test
  @DisplayName("Should throw error when fetching order with invalid id")
  void findOneByIdFailTest() {
    Mockito.when(orderRepository.findById(anyInt())).thenReturn(Optional.empty());
    assertThrowsExactly(EntityNotFoundException.class, () -> orderService.findOne(anyInt()));
  }

  @Test
  @DisplayName("Should return page of orders")
  void findAllTest() {
    @SuppressWarnings("unchecked")
    Page<Order> orders = Mockito.mock(Page.class);
    PageRequest pageable = PageRequest.of(1, 1, Sort.unsorted());

    Mockito.when(orderRepository.findAll(pageable)).thenReturn(orders);
    Page<Order> actualOrderPage = orderService.findAll(1, 1);

    assertEquals(orders, actualOrderPage);
  }

  @Test
  @DisplayName("Should update an order status")
  void updateTest() {
    UpdateOrderDto orderDto = SampleInstanceCollection.getUpdateOrderDto();
    Order expectedOrder = SampleInstanceCollection.getDummyOrder(1);
    Mockito.when(orderRepository.findById(anyInt())).thenReturn(Optional.of(expectedOrder));

    expectedOrder.setStatus(orderDto.getStatus());
    Mockito.when(orderRepository.save(any())).thenReturn(expectedOrder);

    Order actualOrder = orderService.update(1, orderDto);
    assertEquals(expectedOrder, actualOrder);
  }

  @Test
  @DisplayName("Should call delete method of order repository")
  void deleteTest() {
    Mockito.when(orderRepository.findById(anyInt()))
        .thenReturn(Optional.of(SampleInstanceCollection.getDummyOrder(1)));

    orderService.delete(1);
    Mockito.verify(orderRepository).delete(any());
  }
}

