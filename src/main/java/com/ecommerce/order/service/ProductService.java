package com.ecommerce.order.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.order.exception.EntityNotFoundException;
import com.ecommerce.order.model.Product;
import com.ecommerce.order.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    ProductRepository _repository;

    public Product findOne(Integer id) throws EntityNotFoundException {
        Optional<Product> res = _repository.findById(id);
        if (!res.isPresent())
            throw new EntityNotFoundException(Product.class);

        Product product = res.get();
        return product;
    }
}
