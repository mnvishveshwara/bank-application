package com.bankbroker.loanapp.controller.site_visit.impl;

import com.bankbroker.loanapp.controller.site_visit.api.SiteVisitTechnicalSbuaControllerApi;
import com.bankbroker.loanapp.dto.site_visit.SiteVisitTechnicalSbuaRequest;
import com.bankbroker.loanapp.dto.site_visit.SiteVisitTechnicalSbuaResponse;
import com.bankbroker.loanapp.service.site_visit.api.SiteVisitTechnicalSbuaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SiteVisitTechnicalSbuaControllerImpl
        implements SiteVisitTechnicalSbuaControllerApi {

    private final SiteVisitTechnicalSbuaService service;

    @Override
    public ResponseEntity<SiteVisitTechnicalSbuaResponse> save(
            String applicationId,
            SiteVisitTechnicalSbuaRequest request) {

        return ResponseEntity.ok(
                service.save(applicationId, request)
        );
    }

    @Override
    public ResponseEntity<SiteVisitTechnicalSbuaResponse> get(
            String applicationId) {

        return ResponseEntity.ok(
                service.get(applicationId)
        );
    }
}
