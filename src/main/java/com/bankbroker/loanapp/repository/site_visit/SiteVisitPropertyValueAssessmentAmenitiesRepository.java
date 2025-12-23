package com.bankbroker.loanapp.repository.site_visit;

import com.bankbroker.loanapp.entity.core.LoanApplication;
import com.bankbroker.loanapp.entity.site_visit.SiteVisitPropertyValueAssessmentAmenities;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SiteVisitPropertyValueAssessmentAmenitiesRepository
        extends JpaRepository<SiteVisitPropertyValueAssessmentAmenities, Long> {

    Optional<SiteVisitPropertyValueAssessmentAmenities> findByApplication(
            LoanApplication application
    );
}
