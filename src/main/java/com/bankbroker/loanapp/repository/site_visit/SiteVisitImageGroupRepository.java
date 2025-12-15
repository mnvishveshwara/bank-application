package com.bankbroker.loanapp.repository.site_visit;

import com.bankbroker.loanapp.entity.site_visit.SiteVisitImageGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SiteVisitImageGroupRepository
        extends JpaRepository<SiteVisitImageGroup, Long> {

    Optional<SiteVisitImageGroup> findByApplication_Id(String applicationId);
}