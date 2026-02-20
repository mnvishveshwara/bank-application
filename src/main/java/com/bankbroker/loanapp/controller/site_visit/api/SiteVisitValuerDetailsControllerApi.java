package com.bankbroker.loanapp.controller.site_visit.api;


import com.bankbroker.loanapp.dto.site_visit.SiteVisitValuerDetailsResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/api/applications/{applicationId}/site-visit")
public interface SiteVisitValuerDetailsControllerApi {

    @PostMapping(
            path = "/valuer-details",
            consumes = "multipart/form-data"
    )
    @PreAuthorize("hasAnyRole('AGENCY', 'AGENCY_VALUATOR','BANK_VALUATOR')")
    ResponseEntity<SiteVisitValuerDetailsResponse> upload(
            @PathVariable String applicationId,
            @RequestPart(required = false) MultipartFile organisationSeal,
            @RequestPart(required = false) MultipartFile valuerSignature
    );

    @PreAuthorize("hasAnyRole('AGENCY', 'AGENCY_VALUATOR','BANK_VALUATOR')")
    @GetMapping("/valuer-details")
    ResponseEntity<SiteVisitValuerDetailsResponse> get(
            @PathVariable String applicationId
    );
}