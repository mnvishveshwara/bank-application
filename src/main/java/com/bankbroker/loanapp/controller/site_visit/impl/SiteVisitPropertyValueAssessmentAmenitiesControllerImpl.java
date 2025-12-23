package com.bankbroker.loanapp.controller.site_visit.impl;

import com.bankbroker.loanapp.controller.site_visit.api.SiteVisitPropertyValueAssessmentAmenitiesControllerApi;
import com.bankbroker.loanapp.dto.site_visit.SiteVisitPropertyValueAssessmentAmenitiesRequest;
import com.bankbroker.loanapp.dto.site_visit.SiteVisitPropertyValueAssessmentAmenitiesResponse;
import com.bankbroker.loanapp.service.site_visit.api.SiteVisitPropertyValueAssessmentAmenitiesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SiteVisitPropertyValueAssessmentAmenitiesControllerImpl
        implements SiteVisitPropertyValueAssessmentAmenitiesControllerApi {

    private final SiteVisitPropertyValueAssessmentAmenitiesService service;

    @Override
    public ResponseEntity<SiteVisitPropertyValueAssessmentAmenitiesResponse> save(
            String applicationId,
            SiteVisitPropertyValueAssessmentAmenitiesRequest request) {

        return ResponseEntity.ok(
                service.save(applicationId, request)
        );
    }

    @Override
    public ResponseEntity<SiteVisitPropertyValueAssessmentAmenitiesResponse> get(
            String applicationId) {

        return ResponseEntity.ok(
                service.get(applicationId)
        );
    }
}
