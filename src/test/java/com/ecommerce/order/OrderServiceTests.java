package com.ecommerce.order;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.mockito.ArgumentMatchers.any;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.ecommerce.order.constants.OrderStatus;
import com.ecommerce.order.dto.CreateOrderDto;
import com.ecommerce.order.dto.CreateOrderItemDto;
import com.ecommerce.order.exception.EntityNotFoundException;
import com.ecommerce.order.model.Category;
import com.ecommerce.order.model.Customer;
import com.ecommerce.order.model.Order;
import com.ecommerce.order.model.OrderItem;
import com.ecommerce.order.model.Product;
import com.ecommerce.order.repository.OrderRepository;
import com.ecommerce.order.service.CustomerService;
import com.ecommerce.order.service.OrderService;
import com.ecommerce.order.service.ProductService;

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
				.thenReturn(getDummyCustomer(customerId));

		Mockito.when(productService.findOne(productId))
				.thenReturn(getDummyProduct(productId));

		Order order = Order.builder()
				.customer(customerService.findOne(customerId))
				.status(OrderStatus.INITIATED)
				.build();

		CreateOrderDto orderDto = getCreateOrderDto(customerId);

		Mockito.when(orderRepository.save(any())).thenReturn(order);
		Order newOrder = orderService.create(orderDto);
		assertEquals(newOrder, order);
	}

	@Test
	@DisplayName("Should fail creating order for invalid customer")
	void failCreateOrderInvalidCustomerTest() {
		CreateOrderDto orderDto = getCreateOrderDto(1);

		Mockito.when(customerService.findOne(any())).thenThrow(new EntityNotFoundException(Customer.class));
		assertThrows(EntityNotFoundException.class, () -> orderService.create(orderDto));
	}

	@Test
	@DisplayName("Should fail creating order for invalid product")
	void failCreateOrdreInvalidProductTest() {
		CreateOrderDto orderDto = getCreateOrderDto(1);

		Mockito.when(customerService.findOne(any())).thenReturn(getDummyCustomer(1));
		Mockito.when(productService.findOne(any())).thenThrow(new EntityNotFoundException(Product.class));
		assertThrowsExactly(EntityNotFoundException.class, () -> orderService.create(orderDto));
	}

	@Test
	@DisplayName("Should find order with given id")
	void findOneByIdTest() {
		final int orderId = 1;
		Order order = getDummyOrder(orderId);

		Mockito.when(orderRepository.findById(any())).thenReturn(Optional.of(order));
		Order retrievedOrder = orderService.findOne(orderId);
		assertEquals(retrievedOrder, order);
	}

	private Customer getDummyCustomer(int id) {
		Customer customer = Customer.builder()
				.name("John Doe")
				.phone("+919999999999")
				.email("johndoe@example.com")
				.street("abcd")
				.state("efgh")
				.profilePic("http://lorempixel.com/640/480")
				.countryCode("IN")
				.build();
		customer.setId(id);
		return customer;
	}

	private Product getDummyProduct(int id) {
		Product product = Product.builder()
				.name("Dummy product")
				.category(getDummyCategory(id))
				.image("http://lorempixel.com/640/480")
				.description("Yet another dummy product")
				.unitPrice(25.00)
				.build();
		product.setId(id);
		return product;
	}

	private Order getDummyOrder(int id) {
		Order order = Order.builder()
				.customer(getDummyCustomer(id))
				.status(OrderStatus.INITIATED)
				.build();
		List<OrderItem> orderItems = new ArrayList<OrderItem>();
		orderItems.add(getDummyOrderItem(id));

		order.setOrderItems(orderItems);
		order.setId(id);
		return order;
	}

	private OrderItem getDummyOrderItem(int id) {
		OrderItem orderItem = OrderItem.builder()
				.product(getDummyProduct(id))
				.quantity(5)
				.build();
		orderItem.setId(id);
		return orderItem;
	}

	private Category getDummyCategory(int id) {
		Category category = Category.builder().name("Dummy category").parentCategory(1).build();
		category.setId(id);
		return category;
	}

	private List<CreateOrderItemDto> getCreateOrderItemDto(int productId, int quantity) {
		List<CreateOrderItemDto> orderItems = new ArrayList<CreateOrderItemDto>();
		orderItems.add(CreateOrderItemDto.builder()
				.productId(productId)
				.quantity(quantity)
				.build());

		return orderItems;
	}

	private CreateOrderDto getCreateOrderDto(int customerId) {
		List<CreateOrderItemDto> orderItems = getCreateOrderItemDto(5, 5);

		CreateOrderDto orderDto = CreateOrderDto.builder()
				.customerId(customerId)
				.orderItems(orderItems)
				.build();

		return orderDto;
	}
}
