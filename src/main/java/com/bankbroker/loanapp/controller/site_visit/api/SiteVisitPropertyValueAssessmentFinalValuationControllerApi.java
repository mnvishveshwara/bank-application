package com.bankbroker.loanapp.controller.site_visit.api;

import com.bankbroker.loanapp.dto.site_visit.SiteVisitPropertyValueAssessmentFinalValuationRequest;
import com.bankbroker.loanapp.dto.site_visit.SiteVisitPropertyValueAssessmentFinalValuationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/applications/{applicationId}/property-valuation/final")
public interface SiteVisitPropertyValueAssessmentFinalValuationControllerApi {

    @PostMapping
    ResponseEntity<SiteVisitPropertyValueAssessmentFinalValuationResponse> saveFinalValuation(
            @PathVariable String applicationId,
            @RequestBody SiteVisitPropertyValueAssessmentFinalValuationRequest request
    );

    @GetMapping
    ResponseEntity<SiteVisitPropertyValueAssessmentFinalValuationResponse> getFinalValuation(
            @PathVariable String applicationId
    );
}
