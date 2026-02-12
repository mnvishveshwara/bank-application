package com.bankbroker.loanapp.controller.core.api;


import com.bankbroker.loanapp.dto.application.LoanApplicationResponse;
import com.bankbroker.loanapp.dto.master.AgencyMasterRequest;
import com.bankbroker.loanapp.dto.master.AgencyMasterResponse;
import com.bankbroker.loanapp.dto.stage.ApplicationDecisionRequest;
import com.bankbroker.loanapp.dto.stage.ApplicationHistoryResponse;
import org.springframework.data.domain.Page;
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

    @GetMapping("/get-all-agency/{applicationId}")
    ResponseEntity<List<AgencyMasterResponse>> getAllAgencies(@PathVariable String applicationId);

     @GetMapping

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteAgency(
            @PathVariable Long id
    );

//    @GetMapping("/assigned-applications")
//    ResponseEntity<List<LoanApplicationResponse>> getAssignedApplicationsForAgency();


    @GetMapping("/assigned-applications")
    ResponseEntity<Page<LoanApplicationResponse>> getAssignedApplicationsForAgency(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String status
    );

    @PostMapping("/application/{applicationId}/status")
    ResponseEntity<ApplicationHistoryResponse> updateStatus(
            @PathVariable String applicationId,
            @RequestBody ApplicationDecisionRequest request
    );


}
