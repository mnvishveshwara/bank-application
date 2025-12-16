package com.bankbroker.loanapp.repository.site_visit;

import com.bankbroker.loanapp.entity.core.LoanApplication;
import com.bankbroker.loanapp.entity.site_visit.SiteVisitPropertyBoundaryDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SiteVisitPropertyBoundaryDetailsRepository
        extends JpaRepository<SiteVisitPropertyBoundaryDetails, Long> {

    Optional<SiteVisitPropertyBoundaryDetails> findByApplication(LoanApplication application);
}
