package com.ecommerce.order.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ecommerce.order.constants.OrderStatus;
import com.ecommerce.order.dto.CreateOrderDto;
import com.ecommerce.order.dto.UpdateOrderDto;
import com.ecommerce.order.exception.EntityNotFoundException;
import com.ecommerce.order.model.Customer;
import com.ecommerce.order.model.Order;
import com.ecommerce.order.model.OrderItem;
import com.ecommerce.order.model.Product;
import com.ecommerce.order.repository.OrderRepository;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class OrderService {

    @Autowired
    private OrderRepository _repository;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ProductService productService;

    public Page<Order> findAll(Integer page, Integer size) {
        PageRequest pageable = PageRequest.of(page, size, Sort.unsorted());
        return _repository.findAll(pageable);
    }

    public Order findOne(Integer id) {
        Optional<Order> res = _repository.findById(id);
        if (!res.isPresent())
            throw new EntityNotFoundException(Order.class);

        Order order = res.get();
        return order;
    }

    public Order create(CreateOrderDto orderDto) {
        Customer customer = customerService.findOne(orderDto.getCustomerId());

        Order newOrder = Order.builder().customer(customer).status(OrderStatus.INITIATED)
                .build();

        List<OrderItem> newOrderItems = new ArrayList<OrderItem>();
        orderDto.getOrderItems().forEach(createOrderItemDto -> {
            Product product = productService.findOne(createOrderItemDto.getProductId());
            OrderItem newOrderItem = OrderItem.builder().product(product).quantity(createOrderItemDto.getQuantity())
                    .order(newOrder)
                    .build();

            newOrderItems.add(newOrderItem);
        });
        newOrder.setOrderItems(newOrderItems);

        log.info("Creating new order: {}", newOrder.toString());

        return _repository.save(newOrder);
    }

    public Order update(Integer id, UpdateOrderDto orderDto) {
        Order order = this.findOne(id);
        order.setStatus(orderDto.getStatus());
        return _repository.save(order);
    }

    public void delete(Integer id) {
        Order order = this.findOne(id);
        _repository.delete(order);
    }
}