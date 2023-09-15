package com.tujuhsembilan.database.services;

import com.tujuhsembilan.database.models.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> getAllCustomer();
    Customer getCustomer(Long id);
    Customer storeCustomer(Customer customer);
    void updateCustomer(Customer customer, Long id);
    Boolean deleteCustomer(Long id);
}
