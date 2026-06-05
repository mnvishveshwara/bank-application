package com.bankbroker.loanapp.repository.site_visit;

import com.bankbroker.loanapp.entity.site_visit.SiteVisitImage;
import com.bankbroker.loanapp.entity.site_visit.SiteVisitImageGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SiteVisitImageRepository extends JpaRepository<SiteVisitImage, Long> {

    List<SiteVisitImage> findByImageGroup(SiteVisitImageGroup group);

    Optional<SiteVisitImage> findByImageGroupAndSlotKey(
            SiteVisitImageGroup group,
            String slotKey
    );
}