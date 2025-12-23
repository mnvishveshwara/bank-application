package com.bankbroker.loanapp.repository.site_visit;

import com.bankbroker.loanapp.entity.core.LoanApplication;
import com.bankbroker.loanapp.entity.site_visit.SiteVisitTechnicalAdditional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SiteVisitTechnicalAdditionalRepository
        extends JpaRepository<SiteVisitTechnicalAdditional, Long> {

    Optional<SiteVisitTechnicalAdditional> findByApplication(
            LoanApplication application
    );
}
