package com.bankbroker.loanapp.service;


import com.bankbroker.loanapp.dto.application.LoanApplicationResponse;
import com.bankbroker.loanapp.dto.master.AgencyMasterRequest;
import com.bankbroker.loanapp.dto.master.AgencyMasterResponse;
import com.bankbroker.loanapp.dto.stage.ApplicationDecisionRequest;
import com.bankbroker.loanapp.dto.stage.ApplicationHistoryResponse;
import com.bankbroker.loanapp.dto.stage.ApplicationSummaryResponse;
import com.bankbroker.loanapp.entity.LoanApplication;
import jakarta.transaction.Transactional;

import java.util.List;

public interface AgencyMasterService {

    AgencyMasterResponse createAgency(AgencyMasterRequest request);

    AgencyMasterResponse updateAgency(Long id, AgencyMasterRequest request);

    AgencyMasterResponse getAgency(Long id);

    List<AgencyMasterResponse> getAllAgencies();

    void deleteAgency(Long id);


    List<LoanApplicationResponse> getApplicationsForLoggedInAgency();

    ApplicationHistoryResponse updateApplicationStatus(
            String applicationId,
            ApplicationDecisionRequest request
    );
}