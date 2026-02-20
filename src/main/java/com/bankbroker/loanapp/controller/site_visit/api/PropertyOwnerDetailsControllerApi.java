package com.bankbroker.loanapp.controller.site_visit.api;


import com.bankbroker.loanapp.dto.site_visit.PropertyOwnerDetailsResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/applications")
public interface PropertyOwnerDetailsControllerApi {

    /**
     * Prefill Property Owner Details for Site Visit
     * Used before filling Basic Valuation screen
     */
    @PreAuthorize("hasAnyRole('AGENCY', 'AGENCY_VALUATOR','BANK_VALUATOR')")
    @GetMapping("/{applicationId}/site-visit/property-owner-details")
    ResponseEntity<PropertyOwnerDetailsResponse> getPropertyOwnerDetails(
            @PathVariable String applicationId
    );
}
