package com.beautyshop.crud.repository;

import com.beautyshop.crud.model.CustomerOrder;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerOrderRepository extends MongoRepository<CustomerOrder, String> {
    List<CustomerOrder> findByCustomerId(String customerId);
    boolean existsByCustomerId(String customerId);
}
