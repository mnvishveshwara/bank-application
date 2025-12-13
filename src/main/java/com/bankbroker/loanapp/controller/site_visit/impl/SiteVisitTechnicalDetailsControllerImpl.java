package com.bankbroker.loanapp.controller.site_visit.impl;

import com.bankbroker.loanapp.controller.site_visit.api.SiteVisitTechnicalDetailsControllerApi;
import com.bankbroker.loanapp.dto.site_visit.SiteVisitTechnicalDetailsRequest;
import com.bankbroker.loanapp.dto.site_visit.SiteVisitTechnicalDetailsResponse;
import com.bankbroker.loanapp.service.site_visit.SiteVisitTechnicalDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SiteVisitTechnicalDetailsControllerImpl
        implements SiteVisitTechnicalDetailsControllerApi {

    private final SiteVisitTechnicalDetailsService service;

    @Override
    public ResponseEntity<SiteVisitTechnicalDetailsResponse> save(
            String applicationId,
            SiteVisitTechnicalDetailsRequest request) {

        return ResponseEntity.ok(
                service.saveTechnicalDetails(applicationId, request)
        );
    }

    @Override
    public ResponseEntity<SiteVisitTechnicalDetailsResponse> get(String applicationId) {
        return ResponseEntity.ok(
                service.getTechnicalDetails(applicationId)
        );
    }
}
