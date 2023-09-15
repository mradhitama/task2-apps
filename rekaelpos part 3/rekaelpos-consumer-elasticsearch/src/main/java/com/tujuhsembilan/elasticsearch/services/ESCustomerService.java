package com.tujuhsembilan.elasticsearch.services;

import com.tujuhsembilan.elasticsearch.models.ESCustomer;
import com.tujuhsembilan.elasticsearch.models.MessageTemplate;

public interface ESCustomerService {

    void manageCustomer(MessageTemplate messageTemplate);

    Iterable<ESCustomer> getAllESCustomer();
    ESCustomer getESCustomer(Long id);
}
