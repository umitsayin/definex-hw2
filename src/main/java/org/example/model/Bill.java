package org.example.model;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@ToString
public class Bill extends BaseModel{
    private Order order;
    private double totalPrice;
    private LocalDateTime createdAt;

    public Bill(UUID id, Order order, double totalPrice, LocalDateTime createdAt) {
        super(id);
        this.order = order;
        this.totalPrice = totalPrice;
        this.createdAt = createdAt;
    }
}
