package com.bankbroker.loanapp.repository.site_visit;

import com.bankbroker.loanapp.entity.site_visit.SiteVisitPropertyValueAssessment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SiteVisitPropertyValueAssessmentRepository
        extends JpaRepository<SiteVisitPropertyValueAssessment, Long> {

    Optional<SiteVisitPropertyValueAssessment> findByApplication_Id(String applicationId);
}