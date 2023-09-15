package com.tujuhsembilan.database.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerData {
    private Long id;
    private String name;
    private Integer age;
}
