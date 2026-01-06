package com.bankbroker.loanapp.controller.site_visit.impl;

import com.bankbroker.loanapp.controller.site_visit.api.SiteVisitTechnicalBuaControllerApi;
import com.bankbroker.loanapp.dto.site_visit.SiteVisitTechnicalBuaRequest;
import com.bankbroker.loanapp.dto.site_visit.SiteVisitTechnicalBuaResponse;
import com.bankbroker.loanapp.service.site_visit.api.SiteVisitTechnicalBuaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SiteVisitTechnicalBuaControllerImpl
        implements SiteVisitTechnicalBuaControllerApi {

    private final SiteVisitTechnicalBuaService service;

    @Override
    public ResponseEntity<SiteVisitTechnicalBuaResponse> saveTechnicalBua(
            String applicationId,
            SiteVisitTechnicalBuaRequest request) {

        return ResponseEntity.ok(
                service.saveTechnicalBua(applicationId, request)
        );
    }

    @Override
    public ResponseEntity<SiteVisitTechnicalBuaResponse> getTechnicalBua(
            String applicationId) {

        return ResponseEntity.ok(
                service.getTechnicalBua(applicationId)
        );
    }
}
