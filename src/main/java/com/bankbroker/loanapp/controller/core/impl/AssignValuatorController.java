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
    @PreAuthorize("hasRole('AGENCY')")   // ⭐ Only AGENCY users can assign valuators
    public ResponseEntity<AssignValuatorResponse> assignValuator(
            String applicationId,
            @Valid AssignValuatorRequest request
    ) {
        log.info("Assigning valuator {} to application {}", request.getValuatorId(), applicationId);

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
    @PreAuthorize("hasAnyRole('AGENCY','AGENCY_VALUATOR','ADMIN')")   // you may adjust
    public ResponseEntity<AssignValuatorResponse> getValuatorAssignment(String applicationId) {

        log.info("Fetching valuator assignment for application {}", applicationId);

        AssignValuatorResponse response = assignValuatorService.getValuatorAssignment(applicationId);

        return ResponseEntity.ok(response);
    }

    @Override
    @PreAuthorize("hasRole('AGENCY_VALUATOR')")
    public ResponseEntity<?> scheduleSiteVisit(
            String applicationId,
            @Valid SiteVisitRequest request
    ) {
        log.info("Scheduling site visit for application {} by valuator", applicationId);
        return ResponseEntity.ok(assignValuatorService.scheduleSiteVisit(applicationId, request));
    }

}
