package com.bankbroker.loanapp.repository.site_visit;


import com.bankbroker.loanapp.entity.core.LoanApplication;
import com.bankbroker.loanapp.entity.site_visit.SiteVisitTechnicalPlot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SiteVisitTechnicalPlotRepository
        extends JpaRepository<SiteVisitTechnicalPlot, Long> {

    Optional<SiteVisitTechnicalPlot> findByApplication(LoanApplication application);
}