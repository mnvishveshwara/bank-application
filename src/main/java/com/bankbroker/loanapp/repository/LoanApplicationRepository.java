package com.bankbroker.loanapp.repository;

import com.bankbroker.loanapp.entity.Customer;
import com.bankbroker.loanapp.entity.LoanApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LoanApplicationRepository extends JpaRepository<LoanApplication, String> {
    List<LoanApplication> findByClient(Customer client);

    @Query("""
        SELECT a 
        FROM LoanApplication a
        JOIN ApplicationAgencyAssignment aa ON aa.application = a
        WHERE aa.agency.id = :agencyId
    """)
    List<LoanApplication> findApplicationsByAgencyId(Long agencyId);
}
