package com.beautyshop.crud.service;

import com.beautyshop.crud.model.Category;
import java.util.List;

public interface CategoryService {
    Category add(Category category);
    Category modify(String id, Category category);
    List<Category> show();
    Category showById(String id);
    void delete(String id);
}
