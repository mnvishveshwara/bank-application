package com.bankbroker.loanapp.controller.site_visit.api;

import com.bankbroker.loanapp.dto.site_visit.SiteVisitPropertyValuationBuaRequestDto;
import com.bankbroker.loanapp.dto.site_visit.SiteVisitPropertyValuationBuaResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/applications/{applicationId}/site-visit/property-valuation/bua")
public interface SiteVisitPropertyValuationBuaControllerApi {

    @PreAuthorize("hasAnyRole('AGENCY', 'AGENCY_VALUATOR', 'BANK_VALUATOR')")
    @PostMapping
    ResponseEntity<SiteVisitPropertyValuationBuaResponseDto> saveBuaValuation(
            @PathVariable String applicationId,
            @RequestBody SiteVisitPropertyValuationBuaRequestDto request
    );

    @PreAuthorize("hasAnyRole('AGENCY', 'AGENCY_VALUATOR', 'BANK_VALUATOR', 'MANAGER')")
    @GetMapping
    ResponseEntity<SiteVisitPropertyValuationBuaResponseDto> getBuaValuation(
            @PathVariable String applicationId
    );
}