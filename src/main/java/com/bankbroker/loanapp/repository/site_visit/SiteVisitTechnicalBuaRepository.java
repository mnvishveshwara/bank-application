package com.bankbroker.loanapp.repository.site_visit;

import com.bankbroker.loanapp.entity.core.LoanApplication;
import com.bankbroker.loanapp.entity.site_visit.SiteVisitTechnicalBua;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface SiteVisitTechnicalBuaRepository
        extends JpaRepository<SiteVisitTechnicalBua, Long> {

    Optional<SiteVisitTechnicalBua> findByApplication(LoanApplication application);
}