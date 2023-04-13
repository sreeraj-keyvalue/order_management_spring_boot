package com.ecommerce.order.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.order.dto.CreateCustomerDto;
import com.ecommerce.order.dto.UpdateCustomerDto;
import com.ecommerce.order.exception.ResourceNotFoundException;
import com.ecommerce.order.model.Customer;
import com.ecommerce.order.repository.CustomerRepository;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository _repository;

    public List<Customer> findAll() {
        return _repository.findAll();
    }

    public Customer findOne(Integer id) throws ResourceNotFoundException {
        Optional<Customer> res = _repository.findById(id);
        if (!res.isPresent())
            throw new ResourceNotFoundException("Entity not found");

        Customer customer = res.get();
        return customer;
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

        return _repository.save(newCustomer);
    }

    public Customer update(Integer id, UpdateCustomerDto customerDto) throws ResourceNotFoundException {
        Customer customer = this.findOne(id);

        customer.setName(customerDto.getName());
        customer.setEmail(customerDto.getEmail());
        customer.setPhone(customerDto.getPhone());
        customer.setStreet(customerDto.getStreet());
        customer.setCity(customerDto.getCity());
        customer.setState(customerDto.getState());
        customer.setProfilePic(customerDto.getProfilePic());

        return _repository.save(customer);
    }

    public void delete(Integer id) throws ResourceNotFoundException {
        Customer customer = this.findOne(id);
        _repository.delete(customer);
    }
}
