package com.bankbroker.loanapp.service.core.api;

import com.bankbroker.loanapp.dto.master.AgencyStatus;
import com.bankbroker.loanapp.dto.stage.ApplicationHistoryResponse;

public interface AgencyStatusService {
    ApplicationHistoryResponse updateApplicationStatus(
            String applicationId,
            AgencyStatus status
    );
}
