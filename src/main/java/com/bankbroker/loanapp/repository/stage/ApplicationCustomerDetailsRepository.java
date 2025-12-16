package com.bankbroker.loanapp.repository.stage;

import com.bankbroker.loanapp.entity.core.LoanApplication;
import com.bankbroker.loanapp.entity.stage.ApplicationCustomerDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ApplicationCustomerDetailsRepository extends JpaRepository<ApplicationCustomerDetails, Long> {
    Optional<ApplicationCustomerDetails> findByApplication(LoanApplication application);
}
