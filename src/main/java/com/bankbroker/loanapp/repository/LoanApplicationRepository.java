package com.bankbroker.loanapp.repository;

import com.bankbroker.loanapp.entity.Customer;
import com.bankbroker.loanapp.entity.LoanApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanApplicationRepository extends JpaRepository<LoanApplication, String> {
    List<LoanApplication> findByClient(Customer client);
}
