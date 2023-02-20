package org.example.service;

import org.example.model.Customer;

import java.util.List;
import java.util.UUID;

public interface CustomerService {
    void saveCustomer(Customer customer);
    Customer getCustomerById(UUID customerId);
    List<Customer> getAllCustomer();
    List<Customer> getCustomerListByNameLikeAChar(String character);
}
