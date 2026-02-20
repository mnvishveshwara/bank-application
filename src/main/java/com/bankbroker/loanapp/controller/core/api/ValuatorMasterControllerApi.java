package com.bankbroker.loanapp.controller.core.api;

import com.bankbroker.loanapp.dto.application.LoanApplicationResponse;
import com.bankbroker.loanapp.dto.valuator.ValuatorRequest;
import com.bankbroker.loanapp.dto.valuator.ValuatorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/agency/valuators")
public interface ValuatorMasterControllerApi {

    //  CREATE VALUATOR
    @PostMapping
    ResponseEntity<ValuatorResponse> createValuator(
            @RequestBody ValuatorRequest request
    );

    //  UPDATE VALUATOR
    @PutMapping("/{valuatorId}")
    ResponseEntity<ValuatorResponse> updateValuator(
            @PathVariable Long valuatorId,
            @RequestBody ValuatorRequest request
    );

    // GET SINGLE VALUATOR
    @GetMapping("/{valuatorId}")
    ResponseEntity<ValuatorResponse> getValuator(
            @PathVariable Long valuatorId
    );

    //  GET ALL VALUATORS FOR AGENCY
    @GetMapping
    ResponseEntity<List<ValuatorResponse>> getAllValuators();

    //  DELETE VALUATOR
    @DeleteMapping("/{valuatorId}")
    ResponseEntity<Void> deleteValuator(@PathVariable Long valuatorId);

    @GetMapping("/assignedApplications")
    ResponseEntity<List<LoanApplicationResponse>> getAssignedApplicationsForValuator();
}
