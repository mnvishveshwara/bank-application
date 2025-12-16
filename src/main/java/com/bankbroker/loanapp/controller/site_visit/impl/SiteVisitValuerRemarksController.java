package com.bankbroker.loanapp.controller.site_visit.impl;


import com.bankbroker.loanapp.controller.site_visit.api.SiteVisitValuerRemarksControllerApi;
import com.bankbroker.loanapp.dto.site_visit.SiteVisitValuerRemarksRequest;
import com.bankbroker.loanapp.dto.site_visit.SiteVisitValuerRemarksResponse;
import com.bankbroker.loanapp.service.site_visit.api.SiteVisitValuerRemarksService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SiteVisitValuerRemarksController
        implements SiteVisitValuerRemarksControllerApi {

    private final SiteVisitValuerRemarksService service;

    @Override
    public ResponseEntity<SiteVisitValuerRemarksResponse> saveOrUpdate(
            String applicationId,
            SiteVisitValuerRemarksRequest request) {

        return ResponseEntity.ok(
                service.saveOrUpdate(applicationId, request)
        );
    }

    @Override
    public ResponseEntity<SiteVisitValuerRemarksResponse> get(
            String applicationId) {

        return ResponseEntity.ok(
                service.getByApplicationId(applicationId)
        );
    }
}