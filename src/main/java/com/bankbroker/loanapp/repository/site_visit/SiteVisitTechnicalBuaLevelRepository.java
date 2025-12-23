package com.bankbroker.loanapp.repository.site_visit;

import com.bankbroker.loanapp.entity.site_visit.SiteVisitTechnicalBua;
import com.bankbroker.loanapp.entity.site_visit.SiteVisitTechnicalBuaLevel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SiteVisitTechnicalBuaLevelRepository
        extends JpaRepository<SiteVisitTechnicalBuaLevel, Long> {

    List<SiteVisitTechnicalBuaLevel> findByBua(SiteVisitTechnicalBua bua);
}
