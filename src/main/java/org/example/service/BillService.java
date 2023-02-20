package org.example.service;

import org.example.model.Order;
import org.example.util.MonthEnum;

public interface BillService {
    void saveBill(Order order);
    void showTotalPriceOfBillByCustomerRegisterMonth(MonthEnum monthEnum);
    void showBillListGreaterThanPrice(double price);
    void showAverageWithBillListGreaterThanPrice(double price);
    void showCustomerNameLessThanPrice(double price);
    void showCustomerSectorLessThanAverage(double price);
    void showAll();
}
