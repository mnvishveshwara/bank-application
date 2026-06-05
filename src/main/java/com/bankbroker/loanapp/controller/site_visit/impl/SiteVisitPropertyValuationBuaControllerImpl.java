package com.bankbroker.loanapp.controller.site_visit.impl;

import com.bankbroker.loanapp.controller.site_visit.api.SiteVisitPropertyValuationBuaControllerApi;
import com.bankbroker.loanapp.dto.site_visit.SiteVisitPropertyValuationBuaRequestDto;
import com.bankbroker.loanapp.dto.site_visit.SiteVisitPropertyValuationBuaResponseDto;
import com.bankbroker.loanapp.service.site_visit.api.SiteVisitPropertyValuationBuaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SiteVisitPropertyValuationBuaControllerImpl
        implements SiteVisitPropertyValuationBuaControllerApi {

    private final SiteVisitPropertyValuationBuaService service;

    @Override
    public ResponseEntity<SiteVisitPropertyValuationBuaResponseDto> saveBuaValuation(
            String applicationId,
            SiteVisitPropertyValuationBuaRequestDto request) {

        return ResponseEntity.ok(
                service.save(applicationId, request)
        );
    }

    @Override
    public ResponseEntity<SiteVisitPropertyValuationBuaResponseDto> getBuaValuation(
            String applicationId) {

        return ResponseEntity.ok(
                service.getByApplicationId(applicationId)
        );
    }
}