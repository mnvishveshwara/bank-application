package com.bankbroker.loanapp.repository.stage;

import com.bankbroker.loanapp.entity.core.LoanApplication;
import com.bankbroker.loanapp.entity.stage.ApplicationDocumentDetails;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ApplicationDocumentDetailsRepository extends JpaRepository<ApplicationDocumentDetails, Long> {
//    Optional<ApplicationDocumentDetails> findByApplication(LoanApplication application);
    @EntityGraph(attributePaths = "documents")
    Optional<ApplicationDocumentDetails> findByApplication(LoanApplication application);
}
