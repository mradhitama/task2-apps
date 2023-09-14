package com.tujuhsembilan.rekaelpos.repositories;

import com.tujuhsembilan.rekaelpos.models.Customer;
import com.tujuhsembilan.rekaelpos.models.ESEntities.ESCustomer;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerESRepository extends ElasticsearchRepository<ESCustomer, Long> {
}
