package com.tujuhsembilan.elasticsearch.services;

import com.tujuhsembilan.database.models.MessageTemplate;
import com.tujuhsembilan.elasticsearch.models.ESCustomer;
import com.tujuhsembilan.elasticsearch.repositories.ESCustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ESCustomerServiceImpl implements ESCustomerService {

    @Autowired
    ESCustomerRepository esCustomerRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(ESCustomerServiceImpl.class);

    @Override
    @KafkaListener(topics = "topic-customer", groupId = "consumerGroup")
    public void manageCustomer(MessageTemplate messageTemplate) {
        LOGGER.info(String.format("Message Received -> %s", messageTemplate.toString()));
        String method = messageTemplate.getMethod();
        ESCustomer esCustomer = new ESCustomer();
        esCustomer.setId(messageTemplate.getCustomer().getId());
        esCustomer.setName(messageTemplate.getCustomer().getName());
        esCustomer.setAge(messageTemplate.getCustomer().getAge());
        LOGGER.info(String.format("Customer Ready -> %s", esCustomer.toString()));
        if(method.equals("POST") || method.equals("PUT")){
            esCustomerRepository.save(esCustomer);
        } else if (method.equals("DELETE")){
            esCustomerRepository.deleteById(esCustomer.getId());
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
