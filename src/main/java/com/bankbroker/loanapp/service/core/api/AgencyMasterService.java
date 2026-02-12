package com.bankbroker.loanapp.service.core.api;


import com.bankbroker.loanapp.dto.application.LoanApplicationResponse;
import com.bankbroker.loanapp.dto.master.AgencyMasterRequest;
import com.bankbroker.loanapp.dto.master.AgencyMasterResponse;
import com.bankbroker.loanapp.dto.stage.ApplicationDecisionRequest;
import com.bankbroker.loanapp.dto.stage.ApplicationHistoryResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AgencyMasterService {

    AgencyMasterResponse createAgency(AgencyMasterRequest request);

    AgencyMasterResponse updateAgency(Long id, AgencyMasterRequest request);

    AgencyMasterResponse getAgency(Long id);

    List<AgencyMasterResponse> getAllAgencies(String applicationId);

    void deleteAgency(Long id);


    Page<LoanApplicationResponse> getApplicationsForLoggedInAgency(
            int page,
            int size,
            String search,
            String status
    );
    ApplicationHistoryResponse updateApplicationStatus(
            String applicationId,
            ApplicationDecisionRequest request
    );
}