package com.tujuhsembilan.redis.services;


import com.tujuhsembilan.database.models.CustomerData;
import com.tujuhsembilan.database.models.MessageTemplate;

import java.util.List;

public interface RCustomerServices {
    void manageCustomer(MessageTemplate messageTemplate);
    List<CustomerData> getAllRedisCustomer();
    CustomerData getRedisCustomer(Long id);

}
