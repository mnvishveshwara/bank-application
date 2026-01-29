package com.bankbroker.loanapp.controller.core.impl;

import com.bankbroker.loanapp.dto.application.LoanApplicationResponse;
import com.bankbroker.loanapp.service.core.api.LoanApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/bank/applications")
@RequiredArgsConstructor
public class BankApplicationController {

    private final LoanApplicationService service;

    @GetMapping("/my")
    public ResponseEntity<List<LoanApplicationResponse>> getMyBankApplications() {

        return ResponseEntity.ok(service.getApplicationsForMyBanks());
    }
}