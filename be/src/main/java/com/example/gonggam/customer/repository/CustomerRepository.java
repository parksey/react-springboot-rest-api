package com.example.gonggam.customer.repository;

import com.example.gonggam.customer.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    boolean existsByEmail(String email);
}
