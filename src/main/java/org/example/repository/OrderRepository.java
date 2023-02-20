package org.example.repository;

import org.example.model.Order;

import java.util.*;

public class OrderRepository {
    private final static List<Order> orderList = new ArrayList<>();

    public void saveOrder(Order order){

        orderList.add(order);
    }
    public List<Order> getOrderList(){
        return orderList;
    }
}
