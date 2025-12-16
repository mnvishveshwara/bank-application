package com.bankbroker.loanapp.repository.valuator;

import com.bankbroker.loanapp.entity.core.LoanApplication;
import com.bankbroker.loanapp.entity.valuator.AssignValuator;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AssignValuatorRepository extends JpaRepository<AssignValuator, Long> {

    Optional<AssignValuator> findByApplication(LoanApplication application);
}
