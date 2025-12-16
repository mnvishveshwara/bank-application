package com.bankbroker.loanapp.repository.site_visit;

import com.bankbroker.loanapp.entity.core.LoanApplication;
import com.bankbroker.loanapp.entity.site_visit.SiteVisitPropertyDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SiteVisitPropertyDetailsRepository
        extends JpaRepository<SiteVisitPropertyDetails, Long> {

    Optional<SiteVisitPropertyDetails> findByApplication(LoanApplication application);
}
