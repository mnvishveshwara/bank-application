package com.bankbroker.loanapp.controller.site_visit.api;

import com.bankbroker.loanapp.dto.site_visit.SiteVisitInfrastructureDetailsRequest;
import com.bankbroker.loanapp.dto.site_visit.SiteVisitInfrastructureDetailsResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/applications/{applicationId}/site-visit/infrastructure")
public interface SiteVisitInfrastructureDetailsControllerApi {

    @PreAuthorize("hasRole('AGENCY_VALUATOR')")
    @PostMapping
    ResponseEntity<SiteVisitInfrastructureDetailsResponse> saveInfrastructureDetails(
            @PathVariable String applicationId,
            @RequestBody SiteVisitInfrastructureDetailsRequest request
    );

    @PreAuthorize("hasRole('AGENCY_VALUATOR')")
    @GetMapping
    ResponseEntity<SiteVisitInfrastructureDetailsResponse> getInfrastructureDetails(
            @PathVariable String applicationId
    );
}
