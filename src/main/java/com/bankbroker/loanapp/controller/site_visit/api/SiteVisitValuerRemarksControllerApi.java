package com.bankbroker.loanapp.controller.site_visit.api;


import com.bankbroker.loanapp.dto.site_visit.SiteVisitValuerRemarksRequest;
import com.bankbroker.loanapp.dto.site_visit.SiteVisitValuerRemarksResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/applications/{applicationId}/site-visit/valuer-remarks")
public interface SiteVisitValuerRemarksControllerApi {

    @PostMapping
    ResponseEntity<SiteVisitValuerRemarksResponse> saveOrUpdate(
            @PathVariable String applicationId,
            @RequestBody SiteVisitValuerRemarksRequest request
    );

    @GetMapping
    ResponseEntity<SiteVisitValuerRemarksResponse> get(
            @PathVariable String applicationId
    );
}