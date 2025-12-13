package com.bankbroker.loanapp.controller.site_visit.impl;

import com.bankbroker.loanapp.controller.site_visit.api.SiteVisitPropertyDetailsControllerApi;
import com.bankbroker.loanapp.dto.site_visit.SiteVisitPropertyBoundaryDetailsRequest;
import com.bankbroker.loanapp.dto.site_visit.SiteVisitPropertyBoundaryDetailsResponse;
import com.bankbroker.loanapp.dto.site_visit.SiteVisitPropertyDetailsRequest;
import com.bankbroker.loanapp.dto.site_visit.SiteVisitPropertyDetailsResponse;
import com.bankbroker.loanapp.service.site_visit.SiteVisitPropertyDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SiteVisitPropertyDetailsControllerImpl
        implements SiteVisitPropertyDetailsControllerApi {

    private final SiteVisitPropertyDetailsService service;

    @Override
    public ResponseEntity<SiteVisitPropertyDetailsResponse> savePropertyDetails(
            String applicationId,
            SiteVisitPropertyDetailsRequest request) {

        return ResponseEntity.ok(
                service.savePropertyDetails(applicationId, request)
        );
    }

    @Override
    public ResponseEntity<SiteVisitPropertyBoundaryDetailsResponse> savePropertyBoundaryDetails(
            String applicationId,
            SiteVisitPropertyBoundaryDetailsRequest request) {

        return ResponseEntity.ok(
                service.savePropertyBoundaryDetails(applicationId, request)
        );
    }
}
