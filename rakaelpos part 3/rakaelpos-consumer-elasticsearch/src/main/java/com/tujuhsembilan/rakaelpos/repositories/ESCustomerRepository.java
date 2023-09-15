package com.tujuhsembilan.rakaelpos.repositories;

import com.tujuhsembilan.rakaelpos.models.ESCustomer;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

public interface ESCustomerRepository extends ElasticsearchRepository<ESCustomer, Long> {
}