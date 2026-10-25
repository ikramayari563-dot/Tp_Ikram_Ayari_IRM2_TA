package com.beautyshop.crud.service;

import com.beautyshop.crud.model.Product;
import java.util.List;

public interface ProductService {
    Product add(Product product);
    Product modify(String id, Product product);
    List<Product> show();
    Product showById(String id);
    void delete(String id);
}
