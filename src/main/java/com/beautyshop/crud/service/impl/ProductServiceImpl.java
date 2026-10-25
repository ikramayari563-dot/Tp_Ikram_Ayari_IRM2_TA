package com.beautyshop.crud.service.impl;

import com.beautyshop.crud.exception.ResourceNotFoundException;
import com.beautyshop.crud.model.Product;
import com.beautyshop.crud.repository.*;
import com.beautyshop.crud.service.ProductService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository repository;
    private final CategoryRepository categoryRepository;

    public ProductServiceImpl(ProductRepository repository, CategoryRepository categoryRepository) {
        this.repository = repository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product add(Product product) {
        product.setId(null);
        checkCategory(product);
        return repository.save(product);
    }

    @Override
    public Product modify(String id, Product product) {
        if (!repository.existsById(id)) throw new ResourceNotFoundException("Product", id);
        product.setId(id);
        checkCategory(product);
        return repository.save(product);
    }

    @Override
    public List<Product> show() { return repository.findAll(); }

    @Override
    public Product showById(String id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product", id));
    }

    @Override
    public void delete(String id) {
        if (!repository.existsById(id)) throw new ResourceNotFoundException("Product", id);
        repository.deleteById(id);
    }

    /** Vérifie que la catégorie référencée existe (MongoDB n'a pas de clé étrangère). */
    private void checkCategory(Product product) {
        if (!categoryRepository.existsById(product.getCategoryId()))
            throw new ResourceNotFoundException("Category", product.getCategoryId());
    }
}
