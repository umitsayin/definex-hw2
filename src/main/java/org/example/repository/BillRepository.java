package org.example.repository;

import org.example.model.Bill;
import org.example.model.Order;

import java.time.LocalDateTime;
import java.util.*;

public class BillRepository {
    private final static List<Bill> billList = new ArrayList<>();

    public void saveBill(Order order, double price){
        billList.add(new Bill(UUID.randomUUID(),order, price, LocalDateTime.now()));
    }

    public List<Bill> getBillList(){
        return billList;
    }
}
