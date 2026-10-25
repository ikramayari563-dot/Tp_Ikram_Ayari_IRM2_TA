package com.beautyshop.crud.service;

import com.beautyshop.crud.model.Customer;
import java.util.List;

public interface CustomerService {
    Customer add(Customer customer);
    Customer modify(String id, Customer customer);
    List<Customer> show();
    Customer showById(String id);
    void delete(String id);
}
