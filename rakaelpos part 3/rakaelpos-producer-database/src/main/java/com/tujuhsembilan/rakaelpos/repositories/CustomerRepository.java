package com.tujuhsembilan.rakaelpos.repositories;

import com.tujuhsembilan.rakaelpos.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByName(String name);
}
