package com.bankbroker.loanapp.controller.site_visit.api;

import com.bankbroker.loanapp.dto.site_visit.SiteVisitPropertyValueAssessmentAmenitiesRequest;
import com.bankbroker.loanapp.dto.site_visit.SiteVisitPropertyValueAssessmentAmenitiesResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/applications/{applicationId}/site-visit/property-valuation/amenities")
public interface SiteVisitPropertyValueAssessmentAmenitiesControllerApi {

    @PostMapping
    ResponseEntity<SiteVisitPropertyValueAssessmentAmenitiesResponse> save(
            @PathVariable String applicationId,
            @RequestBody SiteVisitPropertyValueAssessmentAmenitiesRequest request
    );

    @GetMapping
    ResponseEntity<SiteVisitPropertyValueAssessmentAmenitiesResponse> get(
            @PathVariable String applicationId
    );
}
