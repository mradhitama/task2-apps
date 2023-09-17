package com.tujuhsembilan.redis.services;

import com.tujuhsembilan.database.models.CustomerData;
import com.tujuhsembilan.database.models.MessageTemplate;
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
        CustomerData customerData = new CustomerData();
        customerData.setId(messageTemplate.getCustomer().getId());
        customerData.setName(messageTemplate.getCustomer().getName());
        customerData.setAge(messageTemplate.getCustomer().getAge());
        if(method.equals("POST") || method.equals("PUT")){
            redisTemplate.opsForHash().put(KEY, customerData.getId(), customerData);
        } else if (method.equals("DELETE")){
            redisTemplate.opsForHash().delete(KEY, customerData.getId());
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
