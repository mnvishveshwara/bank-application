package com.bankbroker.loanapp.repository.site_visit;

import com.bankbroker.loanapp.entity.core.LoanApplication;
import com.bankbroker.loanapp.entity.site_visit.SiteVisitPropertyValueAssessmentLand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SiteVisitPropertyValueAssessmentLandRepository
        extends JpaRepository<SiteVisitPropertyValueAssessmentLand, Long> {

    Optional<SiteVisitPropertyValueAssessmentLand> findByApplication(
            LoanApplication application
    );
}