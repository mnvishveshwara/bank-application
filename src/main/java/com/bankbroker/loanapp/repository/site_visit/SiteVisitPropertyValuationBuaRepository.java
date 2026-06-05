package com.bankbroker.loanapp.repository.site_visit;

import com.bankbroker.loanapp.entity.site_visit.SiteVisitPropertyValuationBua;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SiteVisitPropertyValuationBuaRepository
        extends JpaRepository<SiteVisitPropertyValuationBua, Long> {

    Optional<SiteVisitPropertyValuationBua> findByApplicationId(String applicationId);

    boolean existsByApplicationId(String applicationId);
}