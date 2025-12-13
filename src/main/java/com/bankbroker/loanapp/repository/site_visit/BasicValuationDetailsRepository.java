package com.bankbroker.loanapp.repository.site_visit;

import com.bankbroker.loanapp.entity.LoanApplication;
import com.bankbroker.loanapp.entity.site_visit.BasicValuationDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BasicValuationDetailsRepository
        extends JpaRepository<BasicValuationDetails, Long> {

    Optional<BasicValuationDetails> findByApplication(LoanApplication application);
}
