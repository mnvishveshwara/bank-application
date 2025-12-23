package com.bankbroker.loanapp.repository.site_visit;

import com.bankbroker.loanapp.entity.site_visit.SiteVisitTechnicalLand;
import com.bankbroker.loanapp.entity.site_visit.SiteVisitTechnicalLandImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SiteVisitTechnicalLandImageRepository
        extends JpaRepository<SiteVisitTechnicalLandImage, Long> {

    List<SiteVisitTechnicalLandImage> findByTechnicalLand(
            SiteVisitTechnicalLand land
    );
}

