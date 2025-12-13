package com.bankbroker.loanapp.controller.site_visit.api;

import com.bankbroker.loanapp.dto.site_visit.SiteVisitTechnicalDetailsRequest;
import com.bankbroker.loanapp.dto.site_visit.SiteVisitTechnicalDetailsResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/applications/{applicationId}/site-visit/technical")
public interface SiteVisitTechnicalDetailsControllerApi {

    @PostMapping
    ResponseEntity<SiteVisitTechnicalDetailsResponse> save(
            @PathVariable String applicationId,
            @RequestBody SiteVisitTechnicalDetailsRequest request
    );

    @GetMapping
    ResponseEntity<SiteVisitTechnicalDetailsResponse> get(
            @PathVariable String applicationId
    );
}
