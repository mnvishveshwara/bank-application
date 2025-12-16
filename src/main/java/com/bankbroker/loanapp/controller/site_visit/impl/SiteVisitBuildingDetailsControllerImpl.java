package com.bankbroker.loanapp.controller.site_visit.impl;


import com.bankbroker.loanapp.controller.site_visit.api.SiteVisitBuildingDetailsControllerApi;
import com.bankbroker.loanapp.dto.site_visit.SiteVisitBuildingDetailsRequest;
import com.bankbroker.loanapp.dto.site_visit.SiteVisitBuildingDetailsResponse;
import com.bankbroker.loanapp.service.site_visit.api.SiteVisitBuildingDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SiteVisitBuildingDetailsControllerImpl
        implements SiteVisitBuildingDetailsControllerApi {

    private final SiteVisitBuildingDetailsService service;

    @Override
    public ResponseEntity<SiteVisitBuildingDetailsResponse> saveBuildingDetails(
            String applicationId,
            SiteVisitBuildingDetailsRequest request) {

        return ResponseEntity.ok(
                service.saveBuildingDetails(applicationId, request)
        );
    }

    @Override
    public ResponseEntity<SiteVisitBuildingDetailsResponse> getBuildingDetails(
            String applicationId) {

        return ResponseEntity.ok(
                service.getBuildingDetails(applicationId)
        );
    }
}