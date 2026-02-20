package com.bankbroker.loanapp.controller.site_visit.api;

import com.bankbroker.loanapp.dto.site_visit.SiteVisitTechnicalLandDetailsResponse;
import com.bankbroker.loanapp.dto.site_visit.SiteVisitTechnicalLandRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequestMapping("/api/applications/{applicationId}/site-visit/technical/land")
public interface SiteVisitTechnicalLandControllerApi {
    @PreAuthorize("hasAnyRole('AGENCY', 'AGENCY_VALUATOR','BANK_VALUATOR')")
    @PostMapping
    ResponseEntity<SiteVisitTechnicalLandDetailsResponse> save(
            @PathVariable String applicationId,
            @RequestBody SiteVisitTechnicalLandRequest request
    );
    @PreAuthorize("hasAnyRole('AGENCY', 'AGENCY_VALUATOR','BANK_VALUATOR')")
    @GetMapping
    ResponseEntity<SiteVisitTechnicalLandDetailsResponse> get(
            @PathVariable String applicationId
    );
    @PreAuthorize("hasAnyRole('AGENCY', 'AGENCY_VALUATOR','BANK_VALUATOR')")
    @PostMapping(value = "/images", consumes = "multipart/form-data")
    ResponseEntity<String> uploadImages(
            @PathVariable String applicationId,
            @RequestPart("files") List<MultipartFile> files
    );
}
