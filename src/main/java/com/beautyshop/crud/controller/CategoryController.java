package com.beautyshop.crud.controller;

import com.beautyshop.crud.model.Category;
import com.beautyshop.crud.service.CategoryService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    private final CategoryService service;

    public CategoryController(CategoryService service) { this.service = service; }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Category add(@Valid @RequestBody Category category) { return service.add(category); }

    @PutMapping("/{id}")
    public Category modify(@PathVariable String id, @Valid @RequestBody Category category) { return service.modify(id, category); }

    @GetMapping
    public List<Category> show() { return service.show(); }

    @GetMapping("/{id}")
    public Category showById(@PathVariable String id) { return service.showById(id); }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) { service.delete(id); }
}
