package com.beautyshop.crud.repository;

import com.beautyshop.crud.model.Customer;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer, String> {
    boolean existsByEmail(String email);
    Optional<Customer> findByEmail(String email);
}
