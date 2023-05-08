package com.ecommerce.order.service;

import com.ecommerce.order.dto.CreateCustomerDto;
import com.ecommerce.order.dto.UpdateCustomerDto;
import com.ecommerce.order.exception.EntityNotFoundException;
import com.ecommerce.order.model.Customer;
import com.ecommerce.order.repository.CustomerRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

  @Autowired
  private CustomerRepository customerRepository;

  public List<Customer> findAll() {
    return customerRepository.findAll();
  }

  public Customer findOne(Integer id) throws EntityNotFoundException {
    Optional<Customer> res = customerRepository.findById(id);
    if (!res.isPresent()) {
      throw new EntityNotFoundException(Customer.class);
    }

    return res.get();
  }

  public Customer create(CreateCustomerDto customerDto) {
    Customer newCustomer = Customer.builder().name(customerDto.getName())
        .email(customerDto.getEmail()).phone(customerDto.getPhone()).street(customerDto.getStreet())
        .city(customerDto.getCity()).state(customerDto.getState())
        .profilePic(customerDto.getProfilePic()).build();

    return customerRepository.save(newCustomer);
  }

  public Customer update(Integer id, UpdateCustomerDto customerDto) throws EntityNotFoundException {
    Customer customer = this.findOne(id);

    Customer updatedCustomer = Customer.builder().name(customerDto.getName())
        .email(customerDto.getEmail()).phone(customerDto.getPhone()).street(customerDto.getStreet())
        .city(customerDto.getCity()).state(customerDto.getState())
        .profilePic(customerDto.getProfilePic()).build();

    updatedCustomer.setId(id);

    return customerRepository.save(updatedCustomer);
  }

  public void delete(Integer id) throws EntityNotFoundException {
    Customer customer = this.findOne(id);
    customerRepository.delete(customer);
  }
}
