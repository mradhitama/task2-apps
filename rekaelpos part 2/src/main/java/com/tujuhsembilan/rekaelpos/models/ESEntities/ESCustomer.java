package com.tujuhsembilan.rekaelpos.models.ESEntities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(indexName = "customer")
public class ESCustomer {
    private Long id;
    private String name;
    private Integer age;
}
