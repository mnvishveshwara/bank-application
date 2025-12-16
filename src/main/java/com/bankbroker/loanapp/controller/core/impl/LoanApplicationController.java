package com.bankbroker.loanapp.controller.core.impl;

import com.bankbroker.loanapp.controller.core.api.LoanApplicationControllerApi;
import com.bankbroker.loanapp.dto.application.LoanApplicationAssignRequest;
import com.bankbroker.loanapp.dto.application.LoanApplicationRequest;
import com.bankbroker.loanapp.dto.application.LoanApplicationResponse;
import com.bankbroker.loanapp.service.core.api.LoanApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LoanApplicationController implements LoanApplicationControllerApi {

    private final LoanApplicationService loanApplicationService;

    @Override
    public ResponseEntity<LoanApplicationResponse> createApplication(LoanApplicationRequest request) {
        return ResponseEntity.ok(loanApplicationService.createApplication(request));
    }

    @Override
    public ResponseEntity<LoanApplicationResponse> getApplicationById(String id) {
        return ResponseEntity.ok(loanApplicationService.getApplicationById(id));
    }

    @Override
    public ResponseEntity<List<LoanApplicationResponse>> getAllApplications() {
        return ResponseEntity.ok(loanApplicationService.getAllApplications());
    }

    @Override
    public ResponseEntity<List<LoanApplicationResponse>> getApplicationsByClient(String clientId) {
        return ResponseEntity.ok(loanApplicationService.getApplicationsByClientId(clientId));
    }

    @Override
    public ResponseEntity<LoanApplicationResponse> assignApplication(String id, LoanApplicationAssignRequest request) {
        return ResponseEntity.ok(loanApplicationService.assignApplication(id, request));
    }

    @Override
    public ResponseEntity<LoanApplicationResponse> updateStatus(String id, boolean active) {
        return ResponseEntity.ok(loanApplicationService.updateApplicationStatus(id, active));
    }
}
