package com.bankbroker.loanapp.controller.site_visit.impl;


import com.bankbroker.loanapp.controller.site_visit.api.SiteVisitPropertyValueAssessmentControllerApi;
import com.bankbroker.loanapp.dto.site_visit.SiteVisitPropertyValueAssessmentRequest;
import com.bankbroker.loanapp.dto.site_visit.SiteVisitPropertyValueAssessmentResponse;
import com.bankbroker.loanapp.service.site_visit.SiteVisitPropertyValueAssessmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SiteVisitPropertyValueAssessmentController
        implements SiteVisitPropertyValueAssessmentControllerApi {

    private final SiteVisitPropertyValueAssessmentService service;

    @Override
    public ResponseEntity<SiteVisitPropertyValueAssessmentResponse> saveOrUpdate(
            String applicationId,
            SiteVisitPropertyValueAssessmentRequest request) {

        return ResponseEntity.ok(
                service.saveOrUpdate(applicationId, request)
        );
    }

    @Override
    public ResponseEntity<SiteVisitPropertyValueAssessmentResponse> getByApplicationId(
            String applicationId) {

        return ResponseEntity.ok(
                service.getByApplicationId(applicationId)
        );
    }
}