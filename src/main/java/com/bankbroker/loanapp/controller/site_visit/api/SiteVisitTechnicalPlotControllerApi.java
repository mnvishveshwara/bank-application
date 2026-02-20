package com.bankbroker.loanapp.controller.site_visit.api;

import com.bankbroker.loanapp.dto.site_visit.SiteVisitTechnicalPlotRequest;
import com.bankbroker.loanapp.dto.site_visit.SiteVisitTechnicalPlotResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/applications/{applicationId}/site-visit/technical/plot")
public interface SiteVisitTechnicalPlotControllerApi {
    @PreAuthorize("hasAnyRole('AGENCY', 'AGENCY_VALUATOR','BANK_VALUATOR')")
    @PostMapping
    ResponseEntity<SiteVisitTechnicalPlotResponse> save(
            @PathVariable String applicationId,
            @RequestBody SiteVisitTechnicalPlotRequest request
    );
    @PreAuthorize("hasAnyRole('AGENCY', 'AGENCY_VALUATOR','BANK_VALUATOR')")
    @GetMapping
    ResponseEntity<SiteVisitTechnicalPlotResponse> get(
            @PathVariable String applicationId
    );
}
