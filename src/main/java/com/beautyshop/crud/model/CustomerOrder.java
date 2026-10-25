package com.beautyshop.crud.model;

import jakarta.validation.constraints.*;
import java.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "orders")
public class CustomerOrder {

    @Id
    private String id;

    private LocalDate orderDate;

    private String status;

    @PositiveOrZero
    private double total;

    @NotBlank
    private String customerId;

    public CustomerOrder() {}

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public LocalDate getOrderDate() { return orderDate; }
    public void setOrderDate(LocalDate orderDate) { this.orderDate = orderDate; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }
    public String getCustomerId() { return customerId; }
    public void setCustomerId(String customerId) { this.customerId = customerId; }
}
