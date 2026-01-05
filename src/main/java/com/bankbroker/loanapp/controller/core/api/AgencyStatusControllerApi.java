package com.bankbroker.loanapp.controller.core.api;

import com.bankbroker.loanapp.dto.master.AgencyStatus;
import com.bankbroker.loanapp.dto.stage.ApplicationHistoryResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/applications")
public interface AgencyStatusControllerApi {

    @PutMapping("/{applicationId}/update-status")
    ResponseEntity<ApplicationHistoryResponse> updateApplicationStatus(
            @PathVariable String applicationId,
            @RequestBody AgencyStatus request
    );
}