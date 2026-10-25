package com.beautyshop.crud.service;

import com.beautyshop.crud.model.CustomerOrder;
import java.util.List;

public interface CustomerOrderService {
    CustomerOrder add(CustomerOrder customerOrder);
    CustomerOrder modify(String id, CustomerOrder customerOrder);
    List<CustomerOrder> show();
    CustomerOrder showById(String id);
    void delete(String id);
}
