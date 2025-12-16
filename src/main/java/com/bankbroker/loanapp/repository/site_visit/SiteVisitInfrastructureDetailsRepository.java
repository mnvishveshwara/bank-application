package com.bankbroker.loanapp.repository.site_visit;

import com.bankbroker.loanapp.entity.core.LoanApplication;
import com.bankbroker.loanapp.entity.site_visit.SiteVisitInfrastructureDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SiteVisitInfrastructureDetailsRepository
        extends JpaRepository<SiteVisitInfrastructureDetails, Long> {

    Optional<SiteVisitInfrastructureDetails> findByApplication(LoanApplication application);
}
