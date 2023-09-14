package com.tujuhsembilan.rekaelpos.services;

import com.tujuhsembilan.rekaelpos.models.Customer;
import com.tujuhsembilan.rekaelpos.models.ESEntities.ESCustomer;

import java.util.List;

public interface CustomerServices {
    //database Functions
    List<Customer> getAllCustomer();
    Customer getCustomer(Long id);
    Customer storeCustomer(Customer customer);
    void updateCustomer(Customer customer, Long id);
    Boolean deleteCustomer(Long id);


    //Redis Functions
    List<Customer> getAllRedisCustomer();
    Customer getRedisCustomer(Long id);

    //ElasticSearch Functions
    Iterable<ESCustomer> getAllESCustomer();
    ESCustomer getESCustomer(Long id);

}
