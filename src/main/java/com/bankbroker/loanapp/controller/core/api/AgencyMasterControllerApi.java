package com.bankbroker.loanapp.controller.core.api;


import com.bankbroker.loanapp.dto.application.LoanApplicationResponse;
import com.bankbroker.loanapp.dto.master.AgencyMasterRequest;
import com.bankbroker.loanapp.dto.master.AgencyMasterResponse;
import com.bankbroker.loanapp.dto.stage.ApplicationDecisionRequest;
import com.bankbroker.loanapp.dto.stage.ApplicationHistoryResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/agency-master")
public interface AgencyMasterControllerApi {

    @PostMapping
    ResponseEntity<AgencyMasterResponse> createAgency(
            @RequestBody AgencyMasterRequest request
    );

    @PutMapping("/{id}")
    ResponseEntity<AgencyMasterResponse> updateAgency(
            @PathVariable Long id,
            @RequestBody AgencyMasterRequest request
    );

    @GetMapping("/{id}")
    ResponseEntity<AgencyMasterResponse> getAgency(
            @PathVariable Long id
    );

    @GetMapping("/get-all-agency")
    ResponseEntity<List<AgencyMasterResponse>> getAllAgencies();

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteAgency(
            @PathVariable Long id
    );

    @GetMapping("/assigned-applications")
    ResponseEntity<List<LoanApplicationResponse>> getAssignedApplicationsForAgency();

    @PostMapping("/application/{applicationId}/status")
    ResponseEntity<ApplicationHistoryResponse> updateStatus(
            @PathVariable String applicationId,
            @RequestBody ApplicationDecisionRequest request
    );


}
