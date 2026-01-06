package com.bankbroker.loanapp.controller.site_visit.api;

import com.bankbroker.loanapp.dto.site_visit.SiteVisitTechnicalBuaRequest;
import com.bankbroker.loanapp.dto.site_visit.SiteVisitTechnicalBuaResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/applications/{applicationId}/site-visit/technical/bua")
public interface SiteVisitTechnicalBuaControllerApi {

    @PostMapping
    ResponseEntity<SiteVisitTechnicalBuaResponse> saveTechnicalBua(
            @PathVariable String applicationId,
            @RequestBody SiteVisitTechnicalBuaRequest request
    );

    @GetMapping
    ResponseEntity<SiteVisitTechnicalBuaResponse> getTechnicalBua(
            @PathVariable String applicationId
    );
}
