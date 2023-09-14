package com.tujuhsembilan.rakaelpos.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MessageTemplate {
    private String method;
    private Customer customer;
}
