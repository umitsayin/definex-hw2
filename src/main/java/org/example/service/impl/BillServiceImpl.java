package org.example.service.impl;

import lombok.extern.slf4j.Slf4j;

import org.example.model.Bill;
import org.example.model.Order;
import org.example.repository.BillRepository;
import org.example.service.BillService;
import org.example.util.MonthEnum;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class BillServiceImpl implements BillService{

    private final BillRepository billRepository = new BillRepository();

    @Override
    public void saveBill(Order order) {
        double price =  order.getProductList().stream().map(item -> item.getPrice()).reduce(0d,(a,b) -> a + b);
        billRepository.saveBill(order, price);

        log.info("Bill created:");
    }

    @Override
    public void showTotalPriceOfBillByCustomerRegisterMonth(MonthEnum monthEnum) {
       final  List<Bill> billList = getBillListByMonth(monthEnum);

       final double totalPrice = calculateTotalPriceWithBillList(billList);

       billList.forEach(bill -> System.out.println(bill.toString()));
       System.out.println("Total invoice amount: " + totalPrice);

    }

    @Override
    public void showBillListGreaterThanPrice(double price) {
        final List<Bill> billList = getBillListGreaterThanPrice(price, billRepository.getBillList());

        System.out.println("List of invoices larger than" + price +" TL :");
        billList.stream().forEach(bill -> bill.getTotalPrice());
    }

    @Override
    public void showAverageWithBillListGreaterThanPrice(double price) {
        final List<Bill> billList = getBillListGreaterThanPrice(price, billRepository.getBillList());

        System.out.println("Average of invoices greater than " + price +" TL :" + calculateAverageWithBillList(billList));
    }

    @Override
    public void showCustomerNameLessThanPrice(double price) {
        final List<Bill> billList = getBillListLessThanPrice(price, billRepository.getBillList());

        billList.stream().forEach(bill -> System.out.println("Customer names of invoices less than "
                        + price +" TL :"
                        + bill.getOrder().getCustomer().getName()));
    }

    @Override
    public void showCustomerSectorLessThanAverage(double price) {
        final List<Bill> billList = getBillListLessThanPrice(price, billRepository.getBillList());

        System.out.println("Sectors of companies with invoice averages less than "
                +  price + " TL : "
                + calculateAverageWithBillList(billList));
    }

    @Override
    public void showAll() {
        List<Bill> billList = billRepository.getBillList();

        billList.stream().forEach(item -> System.out.println(item.toString()));
    }

    private double calculateAverageWithBillList(List<Bill> billList){
        return calculateTotalPriceWithBillList(billList) / billList.size();
    }

    private double calculateTotalPriceWithBillList(List<Bill> billList){
        return billList.stream().map(item -> item.getTotalPrice()).reduce(0d, (a,b) -> a + b);
    }

    private List<Bill> getBillListByMonth(MonthEnum monthEnum){
        return billRepository.getBillList().stream()
                .filter(item -> item.getOrder().getCustomer().getMonthEnum().equals(monthEnum))
                .collect(Collectors.toList());
    }

    private List<Bill> getBillListGreaterThanPrice(double price, List<Bill> billList){
        return  billList.stream()
                .filter(bill -> bill.getTotalPrice() > price).collect(Collectors.toList());
    }

    private List<Bill> getBillListLessThanPrice(double price, List<Bill> billList){
        return  billList.stream()
                .filter(bill -> bill.getTotalPrice() < price).collect(Collectors.toList());
    }
}
