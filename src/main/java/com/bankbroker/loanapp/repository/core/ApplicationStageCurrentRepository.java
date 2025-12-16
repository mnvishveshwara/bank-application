package com.bankbroker.loanapp.repository.core;

import com.bankbroker.loanapp.entity.core.ApplicationStageCurrent;
import com.bankbroker.loanapp.entity.core.LoanApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ApplicationStageCurrentRepository extends JpaRepository<ApplicationStageCurrent, Long> {
    Optional<ApplicationStageCurrent> findByApplication(LoanApplication application);

}
