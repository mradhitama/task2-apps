package com.tujuhsembilan.rakaelpos.services;

import com.tujuhsembilan.rakaelpos.models.ESCustomer;
import com.tujuhsembilan.rakaelpos.models.MessageTemplate;

public interface ESCustomerService {

    void manageCustomer(MessageTemplate messageTemplate);

    Iterable<ESCustomer> getAllESCustomer();
    ESCustomer getESCustomer(Long id);
}
