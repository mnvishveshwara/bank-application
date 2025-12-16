package com.bankbroker.loanapp.repository.stage;

import com.bankbroker.loanapp.entity.core.LoanApplication;
import com.bankbroker.loanapp.entity.stage.ApplicationPropertyDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ApplicationPropertyDetailsRepository extends JpaRepository<ApplicationPropertyDetails, Long> {
    Optional<ApplicationPropertyDetails> findByApplication(LoanApplication application);
}
