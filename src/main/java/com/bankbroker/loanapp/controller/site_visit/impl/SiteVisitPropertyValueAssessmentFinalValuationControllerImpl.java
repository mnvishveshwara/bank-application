package com.bankbroker.loanapp.controller.site_visit.impl;

import com.bankbroker.loanapp.controller.site_visit.api.SiteVisitPropertyValueAssessmentFinalValuationControllerApi;
import com.bankbroker.loanapp.dto.site_visit.SiteVisitPropertyValueAssessmentFinalValuationRequest;
import com.bankbroker.loanapp.dto.site_visit.SiteVisitPropertyValueAssessmentFinalValuationResponse;
import com.bankbroker.loanapp.service.site_visit.api.SiteVisitPropertyValueAssessmentFinalValuationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SiteVisitPropertyValueAssessmentFinalValuationControllerImpl
        implements SiteVisitPropertyValueAssessmentFinalValuationControllerApi {

    private final SiteVisitPropertyValueAssessmentFinalValuationService service;

    @Override
    public ResponseEntity<SiteVisitPropertyValueAssessmentFinalValuationResponse> saveFinalValuation(
            String applicationId,
            SiteVisitPropertyValueAssessmentFinalValuationRequest request) {

        return ResponseEntity.ok(
                service.saveFinalValuation(applicationId, request)
        );
    }

    @Override
    public ResponseEntity<SiteVisitPropertyValueAssessmentFinalValuationResponse> getFinalValuation(
            String applicationId) {

        return ResponseEntity.ok(
                service.getFinalValuation(applicationId)
        );
    }
}
