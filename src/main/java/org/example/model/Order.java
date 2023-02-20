package org.example.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
public class Order extends BaseModel{
    private List<Product> productList;
    private boolean state;
    private Customer customer;

    public Order(UUID id, List<Product> productList, boolean state, Customer customer) {
        super(id);
        this.productList = productList;
        this.state = state;
        this.customer = customer;
    }
}
