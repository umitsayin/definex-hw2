package org.example.model;

import lombok.Data;

import java.util.UUID;

@Data
public class Product extends BaseModel {
    private String name;
    private double price;

    public Product(UUID id, String name, double price){
        super(id);
        this.name = name;
        this.price = price;
    }
}
