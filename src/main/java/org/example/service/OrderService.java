package org.example.service;

import org.example.model.Order;
import org.example.model.Product;

import java.util.List;
import java.util.UUID;

public interface OrderService {
    Order saveOrder(List<Product> productList, UUID customerId);
    void deliveryOrder(UUID orderId);
}
