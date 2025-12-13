package com.bankbroker.loanapp.controller.api;

import com.bankbroker.loanapp.dto.site_visit.SiteVisitRequest;
import com.bankbroker.loanapp.dto.valuator.AssignValuatorRequest;
import com.bankbroker.loanapp.dto.valuator.AssignValuatorResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/applications")
public interface AssignValuatorControllerApi {

    @PostMapping("/{applicationId}/assign-valuator")
    ResponseEntity<AssignValuatorResponse> assignValuator(
            @PathVariable("applicationId") String applicationId,
            @Valid @RequestBody AssignValuatorRequest request
    );

    @GetMapping("/{applicationId}/assigned-valuator")
    ResponseEntity<AssignValuatorResponse> getValuatorAssignment(
            @PathVariable("applicationId") String applicationId
    );


    @PostMapping("/{applicationId}/site-visit")
    ResponseEntity<?> scheduleSiteVisit(
            @PathVariable("applicationId") String applicationId,
            @Valid @RequestBody SiteVisitRequest request
    );

}
