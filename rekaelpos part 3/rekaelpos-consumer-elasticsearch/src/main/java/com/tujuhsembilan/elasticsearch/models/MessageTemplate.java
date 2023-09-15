package com.tujuhsembilan.elasticsearch.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MessageTemplate {
    private String method;
    private CustomerData customer;
}
