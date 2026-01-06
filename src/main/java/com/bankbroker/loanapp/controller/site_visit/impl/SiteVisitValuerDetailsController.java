package com.bankbroker.loanapp.controller.site_visit.impl;


import com.bankbroker.loanapp.controller.site_visit.api.SiteVisitValuerDetailsControllerApi;
import com.bankbroker.loanapp.dto.site_visit.SiteVisitValuerDetailsRequest;
import com.bankbroker.loanapp.dto.site_visit.SiteVisitValuerDetailsResponse;
import com.bankbroker.loanapp.service.site_visit.api.SiteVisitValuerDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequiredArgsConstructor
public class SiteVisitValuerDetailsController
        implements SiteVisitValuerDetailsControllerApi {

    private final SiteVisitValuerDetailsService service;

    @Override
    public ResponseEntity<SiteVisitValuerDetailsResponse> upload(
            String applicationId,
            MultipartFile organisationSeal,
            MultipartFile valuerSignature) {


        SiteVisitValuerDetailsRequest request =
                SiteVisitValuerDetailsRequest.builder()
                        .organisationSeal(organisationSeal)
                        .valuerSignature(valuerSignature)
                        .build();

        return ResponseEntity.ok(
                service.upload(applicationId, request)
        );
    }

    @Override
    public ResponseEntity<SiteVisitValuerDetailsResponse> get(
            String applicationId) {

        return ResponseEntity.ok(
                service.getByApplicationId(applicationId)
        );
    }
}