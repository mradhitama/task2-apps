package com.tujuhsembilan.rekaelpos.controllers;

import com.tujuhsembilan.rekaelpos.models.Customer;
import com.tujuhsembilan.rekaelpos.models.ESEntities.ESCustomer;
import com.tujuhsembilan.rekaelpos.services.CustomerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/esApi")
public class ElasticSearchController {

    @Autowired
    CustomerServices customerServices;

    @GetMapping("/getAllESCustomer")
    @ResponseBody
    public ResponseEntity<?> getAllESCustomerData() {
        Iterable<ESCustomer> customerList = customerServices.getAllESCustomer();
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "Customer loaded successfully");
        response.put("data", customerList);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getESCustomer/{id}")
    @ResponseBody
    public ResponseEntity<?> getESCustomerData(@PathVariable Long id) {
        ESCustomer customer = customerServices.getESCustomer(id);
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "Customer with ID " + id + " loaded successfully");
        response.put("data", customer);
        return ResponseEntity.ok(response);
    }
}
