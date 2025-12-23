package com.bankbroker.loanapp.controller.site_visit.impl;

import com.bankbroker.loanapp.controller.site_visit.api.SiteVisitTechnicalPlotControllerApi;
import com.bankbroker.loanapp.dto.site_visit.SiteVisitTechnicalPlotRequest;
import com.bankbroker.loanapp.dto.site_visit.SiteVisitTechnicalPlotResponse;
import com.bankbroker.loanapp.service.site_visit.api.SiteVisitTechnicalPlotService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SiteVisitTechnicalPlotControllerImpl
        implements SiteVisitTechnicalPlotControllerApi {

    private final SiteVisitTechnicalPlotService service;

    @Override
    public ResponseEntity<SiteVisitTechnicalPlotResponse> save(
            String applicationId,
            SiteVisitTechnicalPlotRequest request) {

        return ResponseEntity.ok(service.save(applicationId, request));
    }

    @Override
    public ResponseEntity<SiteVisitTechnicalPlotResponse> get(String applicationId) {
        return ResponseEntity.ok(service.get(applicationId));
    }
}
