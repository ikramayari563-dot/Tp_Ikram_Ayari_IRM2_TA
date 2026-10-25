package com.beautyshop.crud.controller;

import com.beautyshop.crud.model.CustomerOrder;
import com.beautyshop.crud.service.CustomerOrderService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class CustomerOrderController {
    private final CustomerOrderService service;

    public CustomerOrderController(CustomerOrderService service) { this.service = service; }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerOrder add(@Valid @RequestBody CustomerOrder customerOrder) { return service.add(customerOrder); }

    @PutMapping("/{id}")
    public CustomerOrder modify(@PathVariable String id, @Valid @RequestBody CustomerOrder customerOrder) { return service.modify(id, customerOrder); }

    @GetMapping
    public List<CustomerOrder> show() { return service.show(); }

    @GetMapping("/{id}")
    public CustomerOrder showById(@PathVariable String id) { return service.showById(id); }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) { service.delete(id); }
}
