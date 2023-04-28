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
    Customer newCustomer = new Customer();
    newCustomer.setName(customerDto.getName());
    newCustomer.setEmail(customerDto.getEmail());
    newCustomer.setPhone(customerDto.getPhone());
    newCustomer.setStreet(customerDto.getStreet());
    newCustomer.setCity(customerDto.getCity());
    newCustomer.setState(customerDto.getState());
    newCustomer.setProfilePic(customerDto.getProfilePic());

    return customerRepository.save(newCustomer);
  }

  public Customer update(Integer id, UpdateCustomerDto customerDto) throws EntityNotFoundException {
    Customer customer = this.findOne(id);

    customer.setName(customerDto.getName());
    customer.setEmail(customerDto.getEmail());
    customer.setPhone(customerDto.getPhone());
    customer.setStreet(customerDto.getStreet());
    customer.setCity(customerDto.getCity());
    customer.setState(customerDto.getState());
    customer.setProfilePic(customerDto.getProfilePic());

    return customerRepository.save(customer);
  }

  public void delete(Integer id) throws EntityNotFoundException {
    Customer customer = this.findOne(id);
    customerRepository.delete(customer);
  }
}
