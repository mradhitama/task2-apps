package com.tujuhsembilan.redis.services;

import com.tujuhsembilan.redis.models.CustomerData;
import com.tujuhsembilan.redis.models.MessageTemplate;

import java.util.List;

public interface RCustomerServices {
    void manageCustomer(MessageTemplate messageTemplate);
    List<CustomerData> getAllRedisCustomer();
    CustomerData getRedisCustomer(Long id);

}
