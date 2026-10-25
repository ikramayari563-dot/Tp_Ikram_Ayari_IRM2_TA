package com.beautyshop.crud.controller;

import com.beautyshop.crud.model.Product;
import com.beautyshop.crud.service.ProductService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService service;

    public ProductController(ProductService service) { this.service = service; }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product add(@Valid @RequestBody Product product) { return service.add(product); }

    @PutMapping("/{id}")
    public Product modify(@PathVariable String id, @Valid @RequestBody Product product) { return service.modify(id, product); }

    @GetMapping
    public List<Product> show() { return service.show(); }

    @GetMapping("/{id}")
    public Product showById(@PathVariable String id) { return service.showById(id); }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) { service.delete(id); }
}
