package com.tujuhsembilan.rekaelpos.repositories;

import com.tujuhsembilan.rekaelpos.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByName(String name);
}
