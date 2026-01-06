package com.bankbroker.loanapp.controller.site_visit.api;

import com.bankbroker.loanapp.dto.site_visit.SiteVisitPropertyBoundaryDetailsRequest;
import com.bankbroker.loanapp.dto.site_visit.SiteVisitPropertyBoundaryDetailsResponse;
import com.bankbroker.loanapp.dto.site_visit.SiteVisitPropertyDetailsRequest;
import com.bankbroker.loanapp.dto.site_visit.SiteVisitPropertyDetailsResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/applications")
public interface SiteVisitPropertyDetailsControllerApi {

    @PreAuthorize("hasAnyRole('AGENCY','AGENCY_VALUATOR')")
    @PostMapping("/{applicationId}/site-visit/property-details")
    ResponseEntity<SiteVisitPropertyDetailsResponse> savePropertyDetails(
            @PathVariable String applicationId,
            @RequestBody SiteVisitPropertyDetailsRequest request
    );

    @PreAuthorize("hasAnyRole('AGENCY','AGENCY_VALUATOR')")
    @GetMapping("/{applicationId}/site-visit/property-details")
    ResponseEntity<SiteVisitPropertyDetailsResponse> getPropertyDetails(
            @PathVariable String applicationId
    );

    @PreAuthorize("hasAnyRole('AGENCY','AGENCY_VALUATOR')")
    @PostMapping("/{applicationId}/site-visit/property-boundary-details")
    ResponseEntity<SiteVisitPropertyBoundaryDetailsResponse> savePropertyBoundaryDetails(
            @PathVariable String applicationId,
            @RequestBody SiteVisitPropertyBoundaryDetailsRequest request
    );

    @PreAuthorize("hasAnyRole('AGENCY','AGENCY_VALUATOR')")
    @GetMapping ("/{applicationId}/site-visit/property-boundary-details")
    ResponseEntity<SiteVisitPropertyBoundaryDetailsResponse> getPropertyBoundaryDetails(
            @PathVariable String applicationId
    );
}
