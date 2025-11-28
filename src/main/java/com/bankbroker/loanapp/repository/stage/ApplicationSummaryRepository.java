package com.bankbroker.loanapp.repository.stage;

import com.bankbroker.loanapp.entity.LoanApplication;
import com.bankbroker.loanapp.entity.stage.ApplicationSummary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ApplicationSummaryRepository extends JpaRepository<ApplicationSummary, Long> {
    Optional<ApplicationSummary> findByApplication(LoanApplication application);
}
