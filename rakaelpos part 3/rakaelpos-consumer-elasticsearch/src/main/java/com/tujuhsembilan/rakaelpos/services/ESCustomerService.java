package com.tujuhsembilan.rakaelpos.services;

import com.tujuhsembilan.database.models.MessageTemplate;
import com.tujuhsembilan.rakaelpos.models.ESCustomer;

public interface ESCustomerService {

    void manageCustomer(MessageTemplate messageTemplate);

    Iterable<ESCustomer> getAllESCustomer();
    ESCustomer getESCustomer(Long id);
}
