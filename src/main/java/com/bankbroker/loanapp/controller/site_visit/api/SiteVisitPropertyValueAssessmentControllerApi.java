package com.bankbroker.loanapp.controller.site_visit.api;


import com.bankbroker.loanapp.dto.site_visit.SiteVisitPropertyValueAssessmentRequest;
import com.bankbroker.loanapp.dto.site_visit.SiteVisitPropertyValueAssessmentResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/applications/{applicationId}/site-visit")
public interface SiteVisitPropertyValueAssessmentControllerApi {

    @PostMapping("/property-value-assessment")
    ResponseEntity<SiteVisitPropertyValueAssessmentResponse> saveOrUpdate(
            @PathVariable String applicationId,
            @RequestBody SiteVisitPropertyValueAssessmentRequest request
    );

    @GetMapping("/property-value-assessment")
    ResponseEntity<SiteVisitPropertyValueAssessmentResponse> getByApplicationId(
            @PathVariable String applicationId
    );
}