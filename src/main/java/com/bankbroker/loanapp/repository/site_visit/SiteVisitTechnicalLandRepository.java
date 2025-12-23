package com.bankbroker.loanapp.repository.site_visit;

import com.bankbroker.loanapp.entity.core.LoanApplication;
import com.bankbroker.loanapp.entity.site_visit.SiteVisitTechnicalLand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SiteVisitTechnicalLandRepository
        extends JpaRepository<SiteVisitTechnicalLand, Long> {

    Optional<SiteVisitTechnicalLand> findByApplication(LoanApplication application);
}
