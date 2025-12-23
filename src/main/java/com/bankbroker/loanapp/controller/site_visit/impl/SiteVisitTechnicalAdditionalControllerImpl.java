package com.bankbroker.loanapp.controller.site_visit.impl;

import com.bankbroker.loanapp.controller.site_visit.api.SiteVisitTechnicalAdditionalControllerApi;
import com.bankbroker.loanapp.dto.site_visit.SiteVisitTechnicalAdditionalRequest;
import com.bankbroker.loanapp.dto.site_visit.SiteVisitTechnicalAdditionalResponse;
import com.bankbroker.loanapp.service.site_visit.api.SiteVisitTechnicalAdditionalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SiteVisitTechnicalAdditionalControllerImpl
        implements SiteVisitTechnicalAdditionalControllerApi {

    private final SiteVisitTechnicalAdditionalService service;

    @Override
    public ResponseEntity<SiteVisitTechnicalAdditionalResponse> save(
            String applicationId,
            SiteVisitTechnicalAdditionalRequest request) {

        return ResponseEntity.ok(
                service.save(applicationId, request)
        );
    }

    @Override
    public ResponseEntity<SiteVisitTechnicalAdditionalResponse> get(
            String applicationId) {

        return ResponseEntity.ok(
                service.get(applicationId)
        );
    }
}
