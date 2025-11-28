package com.bankbroker.loanapp.repository;

import com.bankbroker.loanapp.entity.ApplicationStageCurrent;
import com.bankbroker.loanapp.entity.LoanApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ApplicationStageCurrentRepository extends JpaRepository<ApplicationStageCurrent, Long> {
    Optional<ApplicationStageCurrent> findByApplication(LoanApplication application);

}
