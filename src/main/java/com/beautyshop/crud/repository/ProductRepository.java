package com.beautyshop.crud.repository;

import com.beautyshop.crud.model.Product;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
    List<Product> findByNameContainingIgnoreCase(String name);
    List<Product> findByCategoryId(String categoryId);
    boolean existsByCategoryId(String categoryId);
}
