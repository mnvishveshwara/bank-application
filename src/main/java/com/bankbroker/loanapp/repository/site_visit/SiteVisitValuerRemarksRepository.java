package com.bankbroker.loanapp.repository.site_visit;

import com.bankbroker.loanapp.entity.site_visit.SiteVisitValuerRemarks;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SiteVisitValuerRemarksRepository
        extends JpaRepository<SiteVisitValuerRemarks, Long> {

    Optional<SiteVisitValuerRemarks> findByApplication_Id(String applicationId);
}