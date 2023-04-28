package com.ecommerce.order.service;

import com.ecommerce.order.exception.EntityNotFoundException;
import com.ecommerce.order.model.Product;
import com.ecommerce.order.repository.ProductRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

  @Autowired
  ProductRepository productRepository;

  public Product findOne(Integer id) throws EntityNotFoundException {
    Optional<Product> res = productRepository.findById(id);
    if (!res.isPresent()) {
      throw new EntityNotFoundException(Product.class);
    }

    return res.get();
  }
}
