package org.example.repository;

import org.example.model.Customer;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CustomerRepository {

    private static Map<UUID, Customer> customerMap = new HashMap<>();

    public void saveCustomer(Customer customer){
        customerMap.put(customer.getId(),customer);
    }

    public Customer getCustomerById(UUID customerId){
        return customerMap.get(customerId);
    }
    public Map<UUID,Customer> getCustomerList(){
        return customerMap;
    }
}
