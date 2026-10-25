package com.beautyshop.crud.controller;

import com.beautyshop.crud.model.Customer;
import com.beautyshop.crud.service.CustomerService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private final CustomerService service;

    public CustomerController(CustomerService service) { this.service = service; }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Customer add(@Valid @RequestBody Customer customer) { return service.add(customer); }

    @PutMapping("/{id}")
    public Customer modify(@PathVariable String id, @Valid @RequestBody Customer customer) { return service.modify(id, customer); }

    @GetMapping
    public List<Customer> show() { return service.show(); }

    @GetMapping("/{id}")
    public Customer showById(@PathVariable String id) { return service.showById(id); }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) { service.delete(id); }
}
