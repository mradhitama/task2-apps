package com.tujuhsembilan.rakaelpos.services;

import com.tujuhsembilan.rakaelpos.models.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> getAllCustomer();
    Customer getCustomer(Long id);
    Customer storeCustomer(Customer customer);
    void updateCustomer(Customer customer, Long id);
    Boolean deleteCustomer(Long id);
}
