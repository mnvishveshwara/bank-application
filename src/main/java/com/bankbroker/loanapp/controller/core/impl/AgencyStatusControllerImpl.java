package com.bankbroker.loanapp.controller.core.impl;

import com.bankbroker.loanapp.controller.core.api.AgencyStatusControllerApi;
import com.bankbroker.loanapp.dto.master.AgencyStatus;
import com.bankbroker.loanapp.dto.stage.ApplicationHistoryResponse;
import com.bankbroker.loanapp.service.core.api.AgencyStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AgencyStatusControllerImpl implements AgencyStatusControllerApi {

    private final AgencyStatusService agencyStatusService;

    @Override
    public ResponseEntity<ApplicationHistoryResponse> updateApplicationStatus(
            String applicationId,
            AgencyStatus request
    ) {
        ApplicationHistoryResponse response =
                agencyStatusService.updateApplicationStatus(applicationId, request);

        return ResponseEntity.ok(response);
    }
}