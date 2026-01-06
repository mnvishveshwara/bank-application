package com.bankbroker.loanapp.controller.site_visit.impl;



import com.bankbroker.loanapp.controller.site_visit.api.SiteVisitPropertyValueAssessmentLandControllerApi;
import com.bankbroker.loanapp.dto.site_visit.SiteVisitPropertyValueAssessmentLandRequest;
import com.bankbroker.loanapp.dto.site_visit.SiteVisitPropertyValueAssessmentLandResponse;
import com.bankbroker.loanapp.service.site_visit.api.SiteVisitPropertyValueAssessmentLandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SiteVisitPropertyValueAssessmentLandControllerImpl
        implements SiteVisitPropertyValueAssessmentLandControllerApi {

    private final SiteVisitPropertyValueAssessmentLandService service;

    @Override
    public ResponseEntity<SiteVisitPropertyValueAssessmentLandResponse> saveLand(
            String applicationId,
            SiteVisitPropertyValueAssessmentLandRequest request) {

        return ResponseEntity.ok(
                service.saveLand(applicationId, request)
        );
    }

    @Override
    public ResponseEntity<SiteVisitPropertyValueAssessmentLandResponse> getLand(
            String applicationId) {

        return ResponseEntity.ok(
                service.getLand(applicationId)
        );
    }
}