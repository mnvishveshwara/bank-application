package com.bankbroker.loanapp.repository.site_visit;

import com.bankbroker.loanapp.entity.core.LoanApplication;
import com.bankbroker.loanapp.entity.site_visit.SiteVisitTechnicalSbua;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SiteVisitTechnicalSbuaRepository
        extends JpaRepository<SiteVisitTechnicalSbua, Long> {

    Optional<SiteVisitTechnicalSbua> findByApplication(
            LoanApplication application
    );
}
