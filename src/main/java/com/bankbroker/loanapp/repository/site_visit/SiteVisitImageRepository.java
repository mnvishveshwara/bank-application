package com.bankbroker.loanapp.repository.site_visit;


import com.bankbroker.loanapp.entity.site_visit.SiteVisitImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SiteVisitImageRepository
        extends JpaRepository<SiteVisitImage, Long> {
}