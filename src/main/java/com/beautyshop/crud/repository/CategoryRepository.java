package com.beautyshop.crud.repository;

import com.beautyshop.crud.model.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CategoryRepository extends MongoRepository<Category, String> {
    boolean existsByName(String name);
}
