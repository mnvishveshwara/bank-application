package com.bankbroker.loanapp.controller.site_visit.api;

import com.bankbroker.loanapp.dto.site_visit.SiteVisitPropertyValueAssessmentAmenitiesRequest;
import com.bankbroker.loanapp.dto.site_visit.SiteVisitPropertyValueAssessmentAmenitiesResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/applications/{applicationId}/site-visit/property-valuation/amenities")
public interface SiteVisitPropertyValueAssessmentAmenitiesControllerApi {

    @PreAuthorize("hasAnyRole('AGENCY', 'AGENCY_VALUATOR','BANK_VALUATOR')")
    @PostMapping
    ResponseEntity<SiteVisitPropertyValueAssessmentAmenitiesResponse> saveSiteVisitAmenities(
            @PathVariable String applicationId,
            @RequestBody SiteVisitPropertyValueAssessmentAmenitiesRequest request
    );

    @PreAuthorize("hasAnyRole('AGENCY', 'AGENCY_VALUATOR','BANK_VALUATOR')")
    @GetMapping
    ResponseEntity<SiteVisitPropertyValueAssessmentAmenitiesResponse> getSiteVisitAmenities(
            @PathVariable String applicationId
    );
}
