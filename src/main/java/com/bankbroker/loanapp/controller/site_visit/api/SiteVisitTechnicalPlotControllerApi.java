package com.bankbroker.loanapp.controller.site_visit.api;

import com.bankbroker.loanapp.dto.site_visit.SiteVisitTechnicalPlotRequest;
import com.bankbroker.loanapp.dto.site_visit.SiteVisitTechnicalPlotResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/applications/{applicationId}/site-visit/technical/plot")
public interface SiteVisitTechnicalPlotControllerApi {

    @PostMapping
    ResponseEntity<SiteVisitTechnicalPlotResponse> save(
            @PathVariable String applicationId,
            @RequestBody SiteVisitTechnicalPlotRequest request
    );

    @GetMapping
    ResponseEntity<SiteVisitTechnicalPlotResponse> get(
            @PathVariable String applicationId
    );
}
