package com.bankbroker.loanapp.repository.site_visit;


import com.bankbroker.loanapp.entity.site_visit.SiteVisitValuerDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SiteVisitValuerDetailsRepository
        extends JpaRepository<SiteVisitValuerDetails, Long> {

    Optional<SiteVisitValuerDetails> findByApplication_Id(String applicationId);
}
