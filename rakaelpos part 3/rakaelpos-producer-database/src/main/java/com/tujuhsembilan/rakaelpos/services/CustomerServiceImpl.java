package com.tujuhsembilan.rakaelpos.services;

import com.tujuhsembilan.rakaelpos.models.Customer;
import com.tujuhsembilan.rakaelpos.models.MessageTemplate;
import com.tujuhsembilan.rakaelpos.repositories.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService{

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerServiceImpl.class);

    @Autowired
    private KafkaTemplate<String, MessageTemplate> kafkaTemplate;

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public List<Customer> getAllCustomer() {
        List<Customer> customerList = customerRepository.findAll();
        if(customerList.isEmpty()){
            return new ArrayList<Customer>();
        }
        return customerList;
    }

    @Override
    public Customer getCustomer(Long id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if(optionalCustomer.isEmpty()){
            return new Customer();
        }
        Customer customer = optionalCustomer.get();
        return customer;
    }

    @Override
    public Customer storeCustomer(Customer customer) {
        Optional<Customer> existingCustomer = customerRepository.findByName(customer.getName());
        if(existingCustomer.isPresent()){
            throw new IllegalArgumentException("Customer with the same name already exists");
        }
        Customer newCustomer = customerRepository.saveAndFlush(customer);
        MessageTemplate messageTemplate = new MessageTemplate("POST", newCustomer);
        LOGGER.info(String.format("Request to store data -> %s", newCustomer.toString()));
        Message<MessageTemplate> message = MessageBuilder
                .withPayload(messageTemplate)
                .setHeader(KafkaHeaders.TOPIC, "customerTopic")
                .build();
        kafkaTemplate.send(message);
        return newCustomer;
    }

    @Override
    public void updateCustomer(Customer customer, Long id) {
        try {
            Customer newCustomer = customerRepository.save(customer);
            MessageTemplate messageTemplate = new MessageTemplate("PUT", newCustomer);
            LOGGER.info(String.format("Request to update data -> %s", newCustomer.toString()));
            Message<MessageTemplate> message = MessageBuilder
                    .withPayload(messageTemplate)
                    .setHeader(KafkaHeaders.TOPIC, "customerTopic")
                    .build();
            kafkaTemplate.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Boolean deleteCustomer(Long id) {
        try {
            customerRepository.deleteById(id);
            Customer customer = new Customer(id, null);
            MessageTemplate messageTemplate = new MessageTemplate("DELETE", customer);
            LOGGER.info(String.format("Request to delete data with id -> %s", customer.getId()));
            Message<MessageTemplate> message = MessageBuilder
                    .withPayload(messageTemplate)
                    .setHeader(KafkaHeaders.TOPIC, "customerTopic")
                    .build();
            kafkaTemplate.send(message);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}