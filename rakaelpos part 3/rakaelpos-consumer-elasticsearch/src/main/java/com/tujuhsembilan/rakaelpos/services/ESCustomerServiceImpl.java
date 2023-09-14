package com.tujuhsembilan.rakaelpos.services;

import com.tujuhsembilan.rakaelpos.models.ESCustomer;
import com.tujuhsembilan.rakaelpos.models.MessageTemplate;
import com.tujuhsembilan.rakaelpos.repositories.ESCustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ESCustomerServiceImpl implements ESCustomerService {

    @Autowired
    ESCustomerRepository esCustomerRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(ESCustomerServiceImpl.class);


    @Override
    public void manageCustomer(MessageTemplate messageTemplate) {
        LOGGER.info(String.format("Message Received -> %s", messageTemplate.toString()));
        String method = messageTemplate.getMethod();
        if(method.equals("POST") || method.equals("PUT")){
            ESCustomer esCustomer = new ESCustomer(messageTemplate.getCustomer().getId(), messageTemplate.getCustomer().getName());
            esCustomerRepository.save(esCustomer);
        } else if (method.equals("DELETE")){
            esCustomerRepository.deleteById(messageTemplate.getCustomer().getId());
        }
    }

    @Override
    public Iterable<ESCustomer> getAllESCustomer() {
        return esCustomerRepository.findAll();
    }

    @Override
    public ESCustomer getESCustomer(Long id) {
        Optional<ESCustomer> optionalCustomer = esCustomerRepository.findById(id);
        if(optionalCustomer.isEmpty()){
            return new ESCustomer();
        }
        ESCustomer customer = optionalCustomer.get();
        return customer;
    }
}
