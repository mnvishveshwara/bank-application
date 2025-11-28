package com.bankbroker.loanapp.repository;

import com.bankbroker.loanapp.entity.ApplicationStageHistory;
import com.bankbroker.loanapp.entity.LoanApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ApplicationStageHistoryRepository extends JpaRepository<ApplicationStageHistory, Long> {
    Optional<ApplicationStageHistory> findByApplication(LoanApplication application);
}
