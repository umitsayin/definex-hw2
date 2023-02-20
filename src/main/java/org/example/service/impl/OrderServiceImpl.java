package org.example.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.constant.ExceptionConstant;
import org.example.exception.EntityNotFoundException;
import org.example.exception.NoProductFoundInOrder;
import org.example.model.Customer;
import org.example.model.Order;
import org.example.model.Product;
import org.example.repository.OrderRepository;
import org.example.service.BillService;
import org.example.service.CustomerService;
import org.example.service.OrderService;

import java.util.List;
import java.util.UUID;

@Slf4j
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository = new OrderRepository();
    private final BillService billService;
    private final CustomerService customerService;

    public OrderServiceImpl(BillService billService,
                            CustomerService customerService) {
        this.billService = billService;
        this.customerService = customerService;
    }

    @Override
    public Order saveOrder(List<Product> productList, UUID customerId) {
        checkOrderBox(productList);

        final Order order = new Order();
        final Customer customer = customerService.getCustomerById(customerId);

        order.setId(UUID.randomUUID());
        order.setProductList(productList);
        order.setState(false);
        order.setCustomer(customer);

        orderRepository.saveOrder(order);
        log.info("Order created.");

        return order;
    }

    @Override
    public void deliveryOrder(UUID orderId) {
        final Order order = orderRepository.getOrderList().stream()
                .filter(item -> item.getId().equals(orderId))
                .findFirst().orElseThrow(() -> new EntityNotFoundException(ExceptionConstant.ORDER_NOT_FOUND));
        double price = order.getProductList().stream().map(item -> item.getPrice()).reduce(0d,(a,b) -> a +b);
        order.setState(true);


        billService.saveBill(order);
        log.info("Order delivered and bill created");
    }

    private void checkOrderBox(List<Product> productList){
        if(productList.size() <= 0)
            throw new NoProductFoundInOrder(ExceptionConstant.NO_PRODUCT_FOUND_IN_ORDER);
    }
}
