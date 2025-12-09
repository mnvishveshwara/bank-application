package com.bankbroker.loanapp.repository.valuator;


import com.bankbroker.loanapp.entity.AgencyMaster;
import com.bankbroker.loanapp.entity.valuator.ValuatorMaster;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ValuatorMasterRepository extends JpaRepository<ValuatorMaster, Long> {

    List<ValuatorMaster> findByAgency(AgencyMaster agency);
}