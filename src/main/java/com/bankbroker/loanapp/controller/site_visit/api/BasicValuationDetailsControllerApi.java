package com.bankbroker.loanapp.controller.site_visit.api;


import com.bankbroker.loanapp.dto.site_visit.BasicValuationDetailsRequest;
import com.bankbroker.loanapp.dto.site_visit.BasicValuationDetailsResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/api/applications")
public interface BasicValuationDetailsControllerApi {

    /**
     * Valuator fills Step-1: Basic Valuation Details
     */
    @PreAuthorize("hasRole('AGENCY_VALUATOR')")
    @PostMapping("/{applicationId}/site-visit/basic-valuation")
    ResponseEntity<BasicValuationDetailsResponse> saveBasicValuation(
            @PathVariable String applicationId,
            @RequestBody BasicValuationDetailsRequest request
    );

    /**
     * View Basic Valuation Details
     * (Agency admin OR assigned valuator)
     */
    @PreAuthorize("hasAnyRole('AGENCY', 'AGENCY_VALUATOR')")
    @GetMapping("/{applicationId}/site-visit/basic-valuation")
    ResponseEntity<BasicValuationDetailsResponse> getBasicValuation(
            @PathVariable String applicationId
    );
}