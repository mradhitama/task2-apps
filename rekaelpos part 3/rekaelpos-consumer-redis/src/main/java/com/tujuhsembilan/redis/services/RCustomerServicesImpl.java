package com.tujuhsembilan.redis.services;

import com.tujuhsembilan.redis.models.CustomerData;
import com.tujuhsembilan.redis.models.MessageTemplate;
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

    private static final String KEY = "USERS";
    private static final Logger LOGGER = LoggerFactory.getLogger(RCustomerServicesImpl.class);

    @Override
    @KafkaListener(topics = "topic-customer", groupId = "consumerGroup")
    public void manageCustomer(MessageTemplate messageTemplate) {
        LOGGER.info(String.format("Message Received -> %s", messageTemplate.toString()));
        String method = messageTemplate.getMethod();
        CustomerData customerData = new CustomerData(messageTemplate.getCustomer().getId(), messageTemplate.getCustomer().getName());
        if(method.equals("POST") || method.equals("PUT")){
            redisTemplate.opsForHash().put(KEY, customerData.getId(), customerData.getName());
        } else if (method.equals("DELETE")){
            redisTemplate.opsForHash().delete(KEY, messageTemplate.getCustomer().getId());
        }
    }

    @Override
    public List<CustomerData> getAllRedisCustomer() {
        List<CustomerData> customerList = redisTemplate.opsForHash().values(KEY);
        if(customerList.isEmpty()){
            return new ArrayList<CustomerData>();
        }
        return customerList;
    }

    @Override
    public CustomerData getRedisCustomer(Long id) {
        CustomerData customer = (CustomerData) redisTemplate.opsForHash().get(KEY,id);
        return customer;
    }
}
