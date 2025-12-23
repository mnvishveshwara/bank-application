package com.bankbroker.loanapp.controller.site_visit.api;

import com.bankbroker.loanapp.dto.site_visit.SiteVisitTechnicalSbuaRequest;
import com.bankbroker.loanapp.dto.site_visit.SiteVisitTechnicalSbuaResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/applications/{applicationId}/site-visit/technical/sbua")
public interface SiteVisitTechnicalSbuaControllerApi {

    @PostMapping
    ResponseEntity<SiteVisitTechnicalSbuaResponse> save(
            @PathVariable String applicationId,
            @RequestBody SiteVisitTechnicalSbuaRequest request
    );

    @GetMapping
    ResponseEntity<SiteVisitTechnicalSbuaResponse> get(
            @PathVariable String applicationId
    );
}
