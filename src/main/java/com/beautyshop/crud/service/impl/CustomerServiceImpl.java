package com.beautyshop.crud.service.impl;

import com.beautyshop.crud.exception.ConflictException;
import com.beautyshop.crud.exception.ResourceNotFoundException;
import com.beautyshop.crud.model.Customer;
import com.beautyshop.crud.repository.*;
import com.beautyshop.crud.service.CustomerService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository repository;
    private final CustomerOrderRepository orderRepository;

    public CustomerServiceImpl(CustomerRepository repository, CustomerOrderRepository orderRepository) {
        this.repository = repository;
        this.orderRepository = orderRepository;
    }

    @Override
    public Customer add(Customer customer) {
        customer.setId(null);
        return repository.save(customer);
    }

    @Override
    public Customer modify(String id, Customer customer) {
        if (!repository.existsById(id)) throw new ResourceNotFoundException("Customer", id);
        customer.setId(id);
        return repository.save(customer);
    }

    @Override
    public List<Customer> show() { return repository.findAll(); }

    @Override
    public Customer showById(String id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer", id));
    }

    @Override
    public void delete(String id) {
        if (!repository.existsById(id)) throw new ResourceNotFoundException("Customer", id);
        if (orderRepository.existsByCustomerId(id)) throw new ConflictException("Client encore lié à des commandes");
        repository.deleteById(id);
    }
}
