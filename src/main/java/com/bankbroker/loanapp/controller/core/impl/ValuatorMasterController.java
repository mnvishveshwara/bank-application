package com.bankbroker.loanapp.controller.core.impl;

import com.bankbroker.loanapp.controller.core.api.ValuatorMasterControllerApi;
import com.bankbroker.loanapp.dto.application.LoanApplicationResponse;
import com.bankbroker.loanapp.dto.valuator.ValuatorRequest;
import com.bankbroker.loanapp.dto.valuator.ValuatorResponse;
import com.bankbroker.loanapp.entity.core.LoanApplication;
import com.bankbroker.loanapp.service.core.api.ValuatorMasterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ValuatorMasterController implements ValuatorMasterControllerApi {

    private final ValuatorMasterService valuatorService;

    @Override
    public ResponseEntity<ValuatorResponse> createValuator( ValuatorRequest request) {
        return ResponseEntity.ok(valuatorService.createValuator( request));
    }

    @Override
    public ResponseEntity<ValuatorResponse> updateValuator( Long valuatorId, ValuatorRequest request) {
        return ResponseEntity.ok(valuatorService.updateValuator(valuatorId, request));
    }

    @Override
    public ResponseEntity<ValuatorResponse> getValuator( Long valuatorId) {
        return ResponseEntity.ok(valuatorService.getValuator(valuatorId));
    }

    @Override
    public ResponseEntity<List<ValuatorResponse>> getAllValuators() {
        log.info("getAllValuators");
        return ResponseEntity.ok(valuatorService.getAllValuators());
    }

    @Override
    public ResponseEntity<Void> deleteValuator( Long valuatorId) {
        valuatorService.deleteValuator(valuatorId);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<List<LoanApplicationResponse>> getAssignedApplicationsForValuator() {
        return ResponseEntity.ok(valuatorService.getApplicationsForLoggedInValuator());
    }

}
