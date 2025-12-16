package com.bankbroker.loanapp.repository.core;

import com.bankbroker.loanapp.entity.core.Customer;
import com.bankbroker.loanapp.entity.core.LoanApplication;
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

    List<LoanApplication> findByClient_Id(String customerId);

    List<LoanApplication> findApplicationsByValuatorId(String valuatorId);

}
