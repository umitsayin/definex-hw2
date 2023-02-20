package org.example.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.constant.ExceptionConstant;
import org.example.exception.CustomerAlreadyExistsException;
import org.example.exception.CustomerNotFoundException;
import org.example.model.Customer;
import org.example.repository.CustomerRepository;
import org.example.service.CustomerService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository = new CustomerRepository();

    @Override
    public void saveCustomer(Customer customer) {
        try{
            customerAssetControl(customer.getId());
        }catch (CustomerAlreadyExistsException e){
            log.error("Customer {0}, Error:{1}", customer.getName(), e.getMessage());
        }

        customerRepository.saveCustomer(customer);

        log.info("New Customer, " + customer.getName() + " success saved");
    }

    @Override
    public Customer getCustomerById(UUID customerId) {
        Customer customer = null;
        try{
            customer = findCustomerById(customerId);
        }catch (CustomerNotFoundException e){
            log.error("Customer is not found for customerId:{0}, Error:{1}", customerId, e.getMessage());
        }

        log.info("Customer {0} listed.", customer.getName());

        return customer;
    }

    @Override
    public List<Customer> getAllCustomer() {
        final List<Customer> customerList = new ArrayList<>(customerRepository.getCustomerList().values());

        log.info("Customers listed.");

        return customerList;
    }

    @Override
    public List<Customer> getCustomerListByNameLikeAChar(String character) {
        final List<Customer> customerList = new ArrayList<>(customerRepository.getCustomerList().values());

        return customerList.stream()
                .filter(item -> item.getName().toLowerCase().contains(character.toLowerCase()))
                .collect(Collectors.toList());
    }

    private void customerAssetControl(UUID customerId) throws CustomerAlreadyExistsException {
        if(customerRepository.getCustomerList().containsKey(customerId)){
            throw new CustomerAlreadyExistsException(ExceptionConstant.CUSTOMER_ALREADY_EXISTS);
        }
    }

    private Customer findCustomerById(UUID customerId) throws CustomerNotFoundException {
        if(!customerRepository.getCustomerList().containsKey(customerId)){
            throw new CustomerNotFoundException(ExceptionConstant.CUSTOMER_NOT_FOUND);
        }

        return customerRepository.getCustomerList().get(customerId);
    }
}
