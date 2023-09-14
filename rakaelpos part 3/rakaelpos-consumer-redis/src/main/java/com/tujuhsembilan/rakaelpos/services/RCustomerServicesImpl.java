package com.tujuhsembilan.rakaelpos.services;

import com.tujuhsembilan.rakaelpos.models.Customer;
import com.tujuhsembilan.rakaelpos.models.MessageTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RCustomerServicesImpl implements RCustomerServices {

    @Autowired
    RedisTemplate redisTemplate;

    private static final String KEY = "USER";
    private static final Logger LOGGER = LoggerFactory.getLogger(RCustomerServicesImpl.class);

    @Override
    @KafkaListener(topics = "topic-customer", groupId = "consumerGroup")
    public void manageCustomer(MessageTemplate messageTemplate) {
        LOGGER.info(String.format("Message Received -> %s", messageTemplate.toString()));
        String method = messageTemplate.getMethod();
        if(method.equals("POST") || method.equals("PUT")){
            redisTemplate.opsForHash().put(KEY, messageTemplate.getCustomer().getId(), messageTemplate.getCustomer());
        } else if (method.equals("DELETE")){
            redisTemplate.opsForHash().delete(KEY, messageTemplate.getCustomer().getId());
        }
    }

    @Override
    public List<Customer> getAllRedisCustomer() {
        List<Customer> customerList = redisTemplate.opsForHash().values(KEY);
        if(customerList.isEmpty()){
            return new ArrayList<Customer>();
        }
        return customerList;
    }

    @Override
    public Customer getRedisCustomer(Long id) {
        Customer customer = (Customer) redisTemplate.opsForHash().get(KEY,id);
        return customer;
    }
}
