package com.bankbroker.loanapp.repository.core;

import com.bankbroker.loanapp.entity.core.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, String> {
    Optional<Customer> findByEmail(String email);
}
