package com.bankbroker.loanapp.controller.site_visit.api;



import com.bankbroker.loanapp.dto.site_visit.SiteVisitPropertyValueAssessmentLandRequest;
import com.bankbroker.loanapp.dto.site_visit.SiteVisitPropertyValueAssessmentLandResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/applications/{applicationId}/site-visit/property-valuation/land")
public interface SiteVisitPropertyValueAssessmentLandControllerApi {
    @PreAuthorize("hasAnyRole('AGENCY', 'AGENCY_VALUATOR','BANK_VALUATOR')")
    @PostMapping
    ResponseEntity<SiteVisitPropertyValueAssessmentLandResponse> saveLand(
            @PathVariable String applicationId,
            @RequestBody SiteVisitPropertyValueAssessmentLandRequest request
    );
    @PreAuthorize("hasAnyRole('AGENCY', 'AGENCY_VALUATOR','BANK_VALUATOR')")
    @GetMapping
    ResponseEntity<SiteVisitPropertyValueAssessmentLandResponse> getLand(
            @PathVariable String applicationId
    );
}