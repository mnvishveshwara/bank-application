package com.bankbroker.loanapp.controller.site_visit.api;

import com.bankbroker.loanapp.dto.site_visit.SiteVisitTechnicalAdditionalRequest;
import com.bankbroker.loanapp.dto.site_visit.SiteVisitTechnicalAdditionalResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/applications/{applicationId}/site-visit/technical/additional")
public interface SiteVisitTechnicalAdditionalControllerApi {

    @PostMapping
    ResponseEntity<SiteVisitTechnicalAdditionalResponse> saveTechnicalAdditional(
            @PathVariable String applicationId,
            @RequestBody SiteVisitTechnicalAdditionalRequest request
    );

    @GetMapping
    ResponseEntity<SiteVisitTechnicalAdditionalResponse> getTechnicalAdditional(
            @PathVariable String applicationId
    );
}
