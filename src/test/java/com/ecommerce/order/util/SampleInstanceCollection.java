package com.ecommerce.order.util;

import com.ecommerce.order.constants.OrderStatus;
import com.ecommerce.order.dto.CreateOrderDto;
import com.ecommerce.order.dto.CreateOrderItemDto;
import com.ecommerce.order.dto.UpdateOrderDto;
import com.ecommerce.order.model.Category;
import com.ecommerce.order.model.Customer;
import com.ecommerce.order.model.Order;
import com.ecommerce.order.model.OrderItem;
import com.ecommerce.order.model.Product;
import java.util.ArrayList;
import java.util.List;

/**
 * Class to return all dummy values for models and dto.
 */
public final class SampleInstanceCollection {

  /**
   * Returns a dummy customer entity with given id.
   *
   * @param id ID of the customer.
   *
   * @return Customer with given ID.
   */
  public static Customer getDummyCustomer(int id) {
    Customer customer = Customer.builder().name("John Doe").phone("+919999999999")
        .email("johndoe@example.com").street("abcd").state("efgh")
        .profilePic("http://lorempixel.com/640/480").countryCode(91).build();
    customer.setId(id);
    return customer;
  }

  /**
   * Returns a dummy product with given ID.
   *
   * @param id ID of the product.
   *
   * @return Product with given ID.
   */
  public static Product getDummyProduct(int id) {
    Product product = Product.builder().name("Dummy product").category(getDummyCategory(id))
        .image("http://lorempixel.com/640/480").description("Yet another dummy product")
        .unitPrice(25.00).build();
    product.setId(id);
    return product;
  }

  /**
   * Returns a dummy order with the given ID.
   *
   * @param id ID of the order.
   *
   * @return Order with given ID.
   */
  public static Order getDummyOrder(int id) {
    Order order =
        Order.builder().customer(getDummyCustomer(id)).status(OrderStatus.INITIATED).build();
    List<OrderItem> orderItems = new ArrayList<OrderItem>();
    orderItems.add(getDummyOrderItem(id));

    order.setOrderItems(orderItems);
    order.setId(id);
    return order;
  }

  /**
   * Returns a dummy order item with given ID.
   *
   * @param id ID of the order item.
   *
   * @return Order item with the given ID.
   */
  public static OrderItem getDummyOrderItem(int id) {
    OrderItem orderItem = OrderItem.builder().product(getDummyProduct(id)).quantity(5).build();
    orderItem.setId(id);
    return orderItem;
  }

  /**
   * Returns a dummy category with given ID.
   *
   * @param id ID of the category.
   *
   * @return Category with the given ID.
   */
  public static Category getDummyCategory(int id) {
    Category category = Category.builder().name("Dummy category").parentCategory(1).build();
    category.setId(id);
    return category;
  }

  /**
   * Returns a dummy CreateOrderItemDto.
   *
   * @param productId ID of the order item.
   *
   * @param quantity Quantity of the product in the order item.
   *
   * @return CreateOrderItemDto with the given productId and quantity.
   */
  public static List<CreateOrderItemDto> getCreateOrderItemDto(int productId, int quantity) {
    List<CreateOrderItemDto> orderItems = new ArrayList<CreateOrderItemDto>();
    orderItems.add(CreateOrderItemDto.builder().productId(productId).quantity(quantity).build());

    return orderItems;
  }

  /**
   * Returns a dummy CreateOrderDto.
   *
   * @param customerId ID of the customer.
   *
   * @return CreateOrderDto with the given customerId.
   */
  public static CreateOrderDto getCreateOrderDto(int customerId) {
    List<CreateOrderItemDto> orderItems = getCreateOrderItemDto(5, 5);

    CreateOrderDto orderDto =
        CreateOrderDto.builder().customerId(customerId).orderItems(orderItems).build();

    return orderDto;
  }

  /**
   * Returns a dummy UpdateOrderDto.
   *
   * @return UpdateOrderDto.
   */
  public static UpdateOrderDto getUpdateOrderDto() {
    UpdateOrderDto orderDto = new UpdateOrderDto();
    orderDto.setStatus(OrderStatus.OUT_FOR_DELIVERY);
    return orderDto;
  }

  private SampleInstanceCollection() {}
}
