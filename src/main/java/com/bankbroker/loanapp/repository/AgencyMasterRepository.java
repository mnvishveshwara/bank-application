package com.bankbroker.loanapp.repository;

import com.bankbroker.loanapp.entity.AgencyMaster;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgencyMasterRepository extends JpaRepository<AgencyMaster, Long> {
    boolean existsByAgencyName(String agencyName);
}
