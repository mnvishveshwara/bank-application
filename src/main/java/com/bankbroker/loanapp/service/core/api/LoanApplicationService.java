package com.bankbroker.loanapp.service.core.api;

import com.bankbroker.loanapp.dto.application.LoanApplicationAssignRequest;
import com.bankbroker.loanapp.dto.application.LoanApplicationRequest;
import com.bankbroker.loanapp.dto.application.LoanApplicationResponse;

import java.util.List;

public interface LoanApplicationService {

    LoanApplicationResponse createApplication(LoanApplicationRequest request);
    LoanApplicationResponse getApplicationById(String id);
    List<LoanApplicationResponse> getAllApplications();
    List<LoanApplicationResponse> getApplicationsByClientId(String clientId);
    LoanApplicationResponse assignApplication(String applicationId, LoanApplicationAssignRequest request);
    LoanApplicationResponse updateApplicationStatus(String applicationId, boolean active);
}
