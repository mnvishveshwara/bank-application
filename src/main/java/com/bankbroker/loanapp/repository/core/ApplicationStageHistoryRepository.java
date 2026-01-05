package com.bankbroker.loanapp.repository.core;

import com.bankbroker.loanapp.entity.core.ApplicationStageHistory;
import com.bankbroker.loanapp.entity.core.LoanApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ApplicationStageHistoryRepository extends JpaRepository<ApplicationStageHistory, Long> {
    Optional<ApplicationStageHistory> findByApplication(LoanApplication application);

}
