package com.bankbroker.loanapp.controller.site_visit.api;

import com.bankbroker.loanapp.dto.site_visit.SiteVisitBuildingDetailsRequest;
import com.bankbroker.loanapp.dto.site_visit.SiteVisitBuildingDetailsResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/applications")
public interface SiteVisitBuildingDetailsControllerApi {

    @PreAuthorize("hasRole('AGENCY_VALUATOR')")
    @PostMapping("/{applicationId}/site-visit/building-details")
    ResponseEntity<SiteVisitBuildingDetailsResponse> saveBuildingDetails(
            @PathVariable String applicationId,
            @RequestBody SiteVisitBuildingDetailsRequest request
    );

    @PreAuthorize("hasAnyRole('AGENCY', 'AGENCY_VALUATOR')")
    @GetMapping("/{applicationId}/site-visit/building-details")
    ResponseEntity<SiteVisitBuildingDetailsResponse> getBuildingDetails(
            @PathVariable String applicationId
    );
}
