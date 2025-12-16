package com.bankbroker.loanapp.repository.core;

import com.bankbroker.loanapp.entity.core.AgencyMaster;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgencyMasterRepository extends JpaRepository<AgencyMaster, Long> {
    boolean existsByAgencyName(String agencyName);
}
