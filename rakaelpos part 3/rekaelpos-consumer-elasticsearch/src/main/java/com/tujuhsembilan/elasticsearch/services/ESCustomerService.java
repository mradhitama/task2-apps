package com.tujuhsembilan.elasticsearch.services;

import com.tujuhsembilan.database.models.MessageTemplate;
import com.tujuhsembilan.elasticsearch.models.ESCustomer;

public interface ESCustomerService {

    void manageCustomer(MessageTemplate messageTemplate);

    Iterable<ESCustomer> getAllESCustomer();
    ESCustomer getESCustomer(Long id);
}
