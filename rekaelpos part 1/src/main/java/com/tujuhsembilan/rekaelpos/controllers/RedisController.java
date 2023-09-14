package com.tujuhsembilan.rekaelpos.controllers;

import com.tujuhsembilan.rekaelpos.models.Customer;
import com.tujuhsembilan.rekaelpos.services.CustomerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/redisApi")
public class RedisController {

    @Autowired
    CustomerServices customerServices;

    @GetMapping("/getAllRedisCustomer")
    @ResponseBody
    public ResponseEntity<?> getAllRedisCustomerData() {
        List<Customer> customerList = customerServices.getAllRedisCustomer();
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "Customer loaded successfully");
        response.put("data", customerList);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getRedisCustomer/{id}")
    @ResponseBody
    public ResponseEntity<?> getRedisCustomerData(@PathVariable Long id) {
        Customer customer = customerServices.getRedisCustomer(id);
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "Customer with ID " + id + " loaded successfully");
        response.put("data", customer);
        return ResponseEntity.ok(response);
    }
}
