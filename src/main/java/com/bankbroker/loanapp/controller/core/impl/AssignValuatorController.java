package com.bankbroker.loanapp.controller.core.impl;

import com.bankbroker.loanapp.controller.core.api.AssignValuatorControllerApi;
import com.bankbroker.loanapp.dto.site_visit.SiteVisitRequest;
import com.bankbroker.loanapp.dto.valuator.AssignValuatorRequest;
import com.bankbroker.loanapp.dto.valuator.AssignValuatorResponse;
import com.bankbroker.loanapp.service.core.api.AssignValuatorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AssignValuatorController implements AssignValuatorControllerApi {

    private final AssignValuatorService assignValuatorService;

    @Override
    @PreAuthorize("hasRole('AGENCY')")   //   Only AGENCY users can assign valuators
    public ResponseEntity<AssignValuatorResponse> assignValuator(
            String applicationId,
            @Valid AssignValuatorRequest request
    ) {

        AssignValuatorResponse response = assignValuatorService.assignValuator(applicationId, request);

        // Build 201 Created Location URL
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .replacePath("/api/applications/{id}/assigned-valuator")
                .buildAndExpand(applicationId)
                .toUri();

        return ResponseEntity.created(location).body(response);
    }

    @Override
    @PreAuthorize("hasAnyRole('AGENCY','AGENCY_VALUATOR','ADMIN','BANK_VALUATOR)")   // you may adjust
    public ResponseEntity<AssignValuatorResponse> getValuatorAssignment(String applicationId) {


        AssignValuatorResponse response = assignValuatorService.getValuatorAssignment(applicationId);

        return ResponseEntity.ok(response);
    }

    @Override
    @PreAuthorize("hasAnyRole('AGENCY','AGENCY_VALUATOR','BANK_VALUATOR','ADMIN')")   // you may adjust
    public ResponseEntity<?> scheduleSiteVisit(
            String applicationId,
            @Valid SiteVisitRequest request
    ) {
        return ResponseEntity.ok(assignValuatorService.scheduleSiteVisit(applicationId, request));
    }

    @Override
    public ResponseEntity<?> rescheduleSiteVisit(String applicationId, @Valid SiteVisitRequest request) {
        return ResponseEntity.ok(assignValuatorService.reScheduleSiteVisit(applicationId, request));
    }


    @Override
    @PreAuthorize("hasRole('AGENCY')")   //   Only AGENCY users can assign valuators
    public ResponseEntity<AssignValuatorResponse> reAssignValuator(
            String applicationId,
            @Valid AssignValuatorRequest request
    ) {

        AssignValuatorResponse response = assignValuatorService.reAssignValuator(applicationId, request);

        // Build 201 Created Location URL
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .replacePath("/api/applications/{id}/re-assigned-valuator")
                .buildAndExpand(applicationId)
                .toUri();

        return ResponseEntity.created(location).body(response);
    }

}
