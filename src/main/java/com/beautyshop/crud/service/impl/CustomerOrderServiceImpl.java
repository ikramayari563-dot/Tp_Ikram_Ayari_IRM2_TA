package com.beautyshop.crud.service.impl;

import com.beautyshop.crud.exception.ResourceNotFoundException;
import com.beautyshop.crud.model.CustomerOrder;
import com.beautyshop.crud.repository.*;
import com.beautyshop.crud.service.CustomerOrderService;
import java.util.List;
import java.time.LocalDate;
import org.springframework.stereotype.Service;

@Service
public class CustomerOrderServiceImpl implements CustomerOrderService {
    private final CustomerOrderRepository repository;
    private final CustomerRepository customerRepository;

    public CustomerOrderServiceImpl(CustomerOrderRepository repository, CustomerRepository customerRepository) {
        this.repository = repository;
        this.customerRepository = customerRepository;
    }

    @Override
    public CustomerOrder add(CustomerOrder customerOrder) {
        customerOrder.setId(null);
        prepare(customerOrder);
        return repository.save(customerOrder);
    }

    @Override
    public CustomerOrder modify(String id, CustomerOrder customerOrder) {
        if (!repository.existsById(id)) throw new ResourceNotFoundException("CustomerOrder", id);
        customerOrder.setId(id);
        prepare(customerOrder);
        return repository.save(customerOrder);
    }

    @Override
    public List<CustomerOrder> show() { return repository.findAll(); }

    @Override
    public CustomerOrder showById(String id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("CustomerOrder", id));
    }

    @Override
    public void delete(String id) {
        if (!repository.existsById(id)) throw new ResourceNotFoundException("CustomerOrder", id);
        repository.deleteById(id);
    }

    private void prepare(CustomerOrder order) {
        if (!customerRepository.existsById(order.getCustomerId()))
            throw new ResourceNotFoundException("Customer", order.getCustomerId());
        if (order.getOrderDate() == null) order.setOrderDate(LocalDate.now());
        if (order.getStatus() == null || order.getStatus().isBlank()) order.setStatus("PENDING");
    }
}
