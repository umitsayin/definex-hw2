package org.example;

import org.example.model.Customer;
import org.example.model.Order;
import org.example.model.Product;
import org.example.service.BillService;
import org.example.service.CustomerService;
import org.example.service.OrderService;
import org.example.service.impl.BillServiceImpl;
import org.example.service.impl.CustomerServiceImpl;
import org.example.service.impl.OrderServiceImpl;
import org.example.util.MonthEnum;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        CustomerService customerService = new CustomerServiceImpl();
        BillService billService = new BillServiceImpl();
        OrderService orderService = new OrderServiceImpl(billService,customerService);

        Customer customer1 = new Customer(UUID.randomUUID(),"Ümit", "Software Developer", MonthEnum.JANUARY);
        Customer customer2 = new Customer(UUID.randomUUID(),"Cem", "Accountant", MonthEnum.JUNE);
        Customer customer3 = new Customer(UUID.randomUUID(),"Ozan", "Streamer", MonthEnum.APRIL);
        Customer customer4 = new Customer(UUID.randomUUID(),"Murat", "Graphic Designer", MonthEnum.JUNE);
        Customer customer5 = new Customer(UUID.randomUUID(),"Kadir", "Health", MonthEnum.SEPTEMBER);

        customerService.saveCustomer(customer1);
        customerService.saveCustomer(customer2);
        customerService.saveCustomer(customer3);
        customerService.saveCustomer(customer4);
        customerService.saveCustomer(customer5);

        List<Product> productList1 = IntStream.range(0,5)
                .mapToObj(i -> new Product(UUID.randomUUID(),i + "- kulaklık test ürünü ", Math.random() * 300))
                .collect(Collectors.toList());

        List<Product> productList2 = IntStream.range(0,5)
                .mapToObj(i -> new Product(UUID.randomUUID(),i + "- fare test ürünü ", Math.random() * 2000))
                .collect(Collectors.toList());

        List<Product> productList3 = IntStream.range(0,5)
                .mapToObj(i -> new Product(UUID.randomUUID(),i + "- Tablet test ürünü ", Math.random() * 10000))
                .collect(Collectors.toList());

        List<Product> productList4 = IntStream.range(0,5)
                .mapToObj(i -> new Product(UUID.randomUUID(),i + "- saat test ürünü ", Math.random() * 50))
                .collect(Collectors.toList());

        List<Product> productList5 = IntStream.range(0,5)
                .mapToObj(i -> new Product(UUID.randomUUID(),i + "- gözlük test ürünü ", Math.random() * 4000))
                .collect(Collectors.toList());

        Order order1 = orderService.saveOrder(productList1,customer1.getId());
        Order order2 = orderService.saveOrder(productList2,customer2.getId());
        Order order3 = orderService.saveOrder(productList3,customer3.getId());
        Order order4 = orderService.saveOrder(productList4,customer4.getId());
        Order order5 = orderService.saveOrder(productList5,customer5.getId());

        orderService.deliveryOrder(order1.getId());
        orderService.deliveryOrder(order2.getId());
        orderService.deliveryOrder(order3.getId());
        orderService.deliveryOrder(order4.getId());
        orderService.deliveryOrder(order5.getId());

        customerService.getAllCustomer().stream().forEach(item -> System.out.println(item.toString()));

        System.out.println("-----------------------");

        customerService.getCustomerListByNameLikeAChar("C").stream().forEach(item -> System.out.println(item.toString()));

        System.out.println("-----------------------");

        billService.showTotalPriceOfBillByCustomerRegisterMonth(MonthEnum.JUNE);

        System.out.println("-----------------------");

        billService.showAll();

        System.out.println("-----------------------");

        billService.showBillListGreaterThanPrice(1500);

        System.out.println("-----------------------");

        billService.showAverageWithBillListGreaterThanPrice(1500);

        System.out.println("-----------------------");

        billService.showCustomerNameLessThanPrice(500);

        System.out.println("-----------------------");

        billService.showCustomerSectorLessThanAverage(750);


    }
}