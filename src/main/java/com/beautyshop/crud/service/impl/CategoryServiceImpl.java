package com.beautyshop.crud.service.impl;

import com.beautyshop.crud.exception.ConflictException;
import com.beautyshop.crud.exception.ResourceNotFoundException;
import com.beautyshop.crud.model.Category;
import com.beautyshop.crud.repository.*;
import com.beautyshop.crud.service.CategoryService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository repository;
    private final ProductRepository productRepository;

    public CategoryServiceImpl(CategoryRepository repository, ProductRepository productRepository) {
        this.repository = repository;
        this.productRepository = productRepository;
    }

    @Override
    public Category add(Category category) {
        category.setId(null);
        return repository.save(category);
    }

    @Override
    public Category modify(String id, Category category) {
        if (!repository.existsById(id)) throw new ResourceNotFoundException("Category", id);
        category.setId(id);
        return repository.save(category);
    }

    @Override
    public List<Category> show() { return repository.findAll(); }

    @Override
    public Category showById(String id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", id));
    }

    @Override
    public void delete(String id) {
        if (!repository.existsById(id)) throw new ResourceNotFoundException("Category", id);
        if (productRepository.existsByCategoryId(id)) throw new ConflictException("Catégorie encore utilisée par des produits");
        repository.deleteById(id);
    }
}
