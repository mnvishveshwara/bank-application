package com.bankbroker.loanapp.controller;


import com.bankbroker.loanapp.controller.api.AgencyMasterControllerApi;
import com.bankbroker.loanapp.dto.application.LoanApplicationResponse;
import com.bankbroker.loanapp.dto.master.AgencyMasterRequest;
import com.bankbroker.loanapp.dto.master.AgencyMasterResponse;
import com.bankbroker.loanapp.dto.stage.ApplicationDecisionRequest;
import com.bankbroker.loanapp.dto.stage.ApplicationHistoryResponse;
import com.bankbroker.loanapp.service.AgencyMasterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AgencyMasterController implements AgencyMasterControllerApi {

    private final AgencyMasterService service;

    @Override
    public ResponseEntity<AgencyMasterResponse> createAgency(AgencyMasterRequest request) {
        return ResponseEntity.ok(service.createAgency(request));
    }

    @Override
    public ResponseEntity<AgencyMasterResponse> updateAgency(Long id, AgencyMasterRequest request) {
        return ResponseEntity.ok(service.updateAgency(id, request));
    }

    @Override
    public ResponseEntity<AgencyMasterResponse> getAgency(Long id) {
        return ResponseEntity.ok(service.getAgency(id));
    }

    @Override
    public ResponseEntity<List<AgencyMasterResponse>> getAllAgencies() {
        return ResponseEntity.ok(service.getAllAgencies());
    }

    @Override
    public ResponseEntity<Void> deleteAgency(Long id) {
        service.deleteAgency(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<List<LoanApplicationResponse>> getAssignedApplicationsForAgency() {
        return ResponseEntity.ok(service.getApplicationsForLoggedInAgency());
    }


    @Override
    public ResponseEntity<ApplicationHistoryResponse> updateStatus(
            String applicationId,
            ApplicationDecisionRequest request) {

        return ResponseEntity.ok(
                service.updateApplicationStatus(applicationId, request)
        );
    }
}