package com.bankbroker.loanapp.controller.core.api;

import com.bankbroker.loanapp.dto.application.LoanApplicationAssignRequest;
import com.bankbroker.loanapp.dto.application.LoanApplicationRequest;
import com.bankbroker.loanapp.dto.application.LoanApplicationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/applications")
public interface LoanApplicationControllerApi {

    @PostMapping
    ResponseEntity<LoanApplicationResponse> createApplication(@RequestBody LoanApplicationRequest request);

    @GetMapping("/{id}")
    ResponseEntity<LoanApplicationResponse> getApplicationById(@PathVariable String id);

    @GetMapping
    ResponseEntity<List<LoanApplicationResponse>> getAllApplications();

    @GetMapping("/client/{clientId}")
    ResponseEntity<List<LoanApplicationResponse>> getApplicationsByClient(@PathVariable String clientId);

    @PutMapping("/{id}/assign")
    ResponseEntity<LoanApplicationResponse> assignApplication(@PathVariable String id,
                                                              @RequestBody LoanApplicationAssignRequest request);

    @PutMapping("/{id}/status")
    ResponseEntity<LoanApplicationResponse> updateStatus(@PathVariable String id,
                                                         @RequestParam boolean active);
}
