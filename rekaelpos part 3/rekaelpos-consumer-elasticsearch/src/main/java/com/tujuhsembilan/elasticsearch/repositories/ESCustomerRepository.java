package com.tujuhsembilan.elasticsearch.repositories;

import com.tujuhsembilan.elasticsearch.models.ESCustomer;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ESCustomerRepository extends ElasticsearchRepository<ESCustomer, Long> {
}
