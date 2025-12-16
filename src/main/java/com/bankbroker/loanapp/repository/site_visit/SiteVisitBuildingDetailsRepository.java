package com.bankbroker.loanapp.repository.site_visit;

import com.bankbroker.loanapp.entity.core.LoanApplication;
import com.bankbroker.loanapp.entity.site_visit.SiteVisitBuildingDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SiteVisitBuildingDetailsRepository
        extends JpaRepository<SiteVisitBuildingDetails, Long> {

    Optional<SiteVisitBuildingDetails> findByApplication(LoanApplication application);
}
