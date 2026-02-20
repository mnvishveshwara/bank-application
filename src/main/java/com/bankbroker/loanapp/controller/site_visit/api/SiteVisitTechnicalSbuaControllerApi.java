package com.bankbroker.loanapp.controller.site_visit.api;

import com.bankbroker.loanapp.dto.site_visit.SiteVisitTechnicalSbuaRequest;
import com.bankbroker.loanapp.dto.site_visit.SiteVisitTechnicalSbuaResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/applications/{applicationId}/site-visit/technical/sbua")
public interface SiteVisitTechnicalSbuaControllerApi {
    @PreAuthorize("hasAnyRole('AGENCY', 'AGENCY_VALUATOR','BANK_VALUATOR')")
    @PostMapping
    ResponseEntity<SiteVisitTechnicalSbuaResponse> save(
            @PathVariable String applicationId,
            @RequestBody SiteVisitTechnicalSbuaRequest request
    );
    @PreAuthorize("hasAnyRole('AGENCY', 'AGENCY_VALUATOR','BANK_VALUATOR')")
    @GetMapping
    ResponseEntity<SiteVisitTechnicalSbuaResponse> get(
            @PathVariable String applicationId
    );
}
