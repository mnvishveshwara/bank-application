package com.bankbroker.loanapp.controller.site_visit.impl;

import com.bankbroker.loanapp.controller.site_visit.api.SiteVisitInfrastructureDetailsControllerApi;
import com.bankbroker.loanapp.dto.site_visit.SiteVisitInfrastructureDetailsRequest;
import com.bankbroker.loanapp.dto.site_visit.SiteVisitInfrastructureDetailsResponse;
import com.bankbroker.loanapp.service.site_visit.SiteVisitInfrastructureDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SiteVisitInfrastructureDetailsControllerImpl
        implements SiteVisitInfrastructureDetailsControllerApi {

    private final SiteVisitInfrastructureDetailsService service;

    @Override
    public ResponseEntity<SiteVisitInfrastructureDetailsResponse> saveInfrastructureDetails(
            String applicationId,
            SiteVisitInfrastructureDetailsRequest request) {

        return ResponseEntity.ok(
                service.saveInfrastructureDetails(applicationId, request)
        );
    }

    @Override
    public ResponseEntity<SiteVisitInfrastructureDetailsResponse> getInfrastructureDetails(
            String applicationId) {

        return ResponseEntity.ok(
                service.getInfrastructureDetails(applicationId)
        );
    }
}
