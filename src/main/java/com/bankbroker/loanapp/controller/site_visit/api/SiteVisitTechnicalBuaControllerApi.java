package com.bankbroker.loanapp.controller.site_visit.api;

import com.bankbroker.loanapp.dto.site_visit.SiteVisitTechnicalBuaRequest;
import com.bankbroker.loanapp.dto.site_visit.SiteVisitTechnicalBuaResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/applications/{applicationId}/site-visit/technical/bua")
public interface SiteVisitTechnicalBuaControllerApi {
    @PreAuthorize("hasAnyRole('AGENCY', 'AGENCY_VALUATOR','BANK_VALUATOR')")
    @PostMapping
    ResponseEntity<SiteVisitTechnicalBuaResponse> saveTechnicalBua(
            @PathVariable String applicationId,
            @RequestBody SiteVisitTechnicalBuaRequest request
    );

    @PreAuthorize("hasAnyRole('AGENCY', 'AGENCY_VALUATOR','BANK_VALUATOR')")
    @GetMapping
    ResponseEntity<SiteVisitTechnicalBuaResponse> getTechnicalBua(
            @PathVariable String applicationId
    );
}
