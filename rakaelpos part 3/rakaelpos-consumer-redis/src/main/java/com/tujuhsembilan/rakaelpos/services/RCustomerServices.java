package com.tujuhsembilan.rakaelpos.services;

import com.tujuhsembilan.rakaelpos.models.Customer;
import com.tujuhsembilan.rakaelpos.models.MessageTemplate;

import java.util.List;

public interface RCustomerServices {
    void manageCustomer(MessageTemplate messageTemplate);
    List<Customer> getAllRedisCustomer();
    Customer getRedisCustomer(Long id);

}
