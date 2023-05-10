package com.ecommerce.order.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.mockito.ArgumentMatchers.anyInt;

import com.ecommerce.order.exception.EntityNotFoundException;
import com.ecommerce.order.model.Product;
import com.ecommerce.order.repository.ProductRepository;
import com.ecommerce.order.util.SampleInstanceCollection;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class ProductServiceTests {

  @MockBean
  private ProductRepository productRepository;

  @Autowired
  private ProductService productService;

  @Test
  @DisplayName("Should return product with given id")
  void findOneByIdTest() {
    final int productId = 1;
    Product expectedProduct = SampleInstanceCollection.getDummyProduct(productId);

    Mockito.when(productRepository.findById(anyInt())).thenReturn(Optional.of(expectedProduct));
    Product actualProduct = productService.findOne(productId);

    assertEquals(expectedProduct, actualProduct);
  }

  @Test
  @DisplayName("Should throw error when fetching order with invalid id")
  void findOneByIdFailTest() {
    Mockito.when(productRepository.findById(anyInt())).thenReturn(Optional.empty());
    assertThrowsExactly(EntityNotFoundException.class, () -> productService.findOne(anyInt()));
  }

}
