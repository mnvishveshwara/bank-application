package com.bankbroker.loanapp.controller.site_visit.impl;


import com.bankbroker.loanapp.controller.site_visit.api.BasicValuationDetailsControllerApi;
import com.bankbroker.loanapp.dto.site_visit.BasicValuationDetailsRequest;
import com.bankbroker.loanapp.dto.site_visit.BasicValuationDetailsResponse;
//import com.bankbroker.loanapp.service.site_visit.BasicValuationDetailsService;
import com.bankbroker.loanapp.service.site_visit.api.BasicValuationDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequiredArgsConstructor
public class BasicValuationDetailsControllerImpl
        implements BasicValuationDetailsControllerApi {

    private final BasicValuationDetailsService service;

    @Override
    public ResponseEntity<BasicValuationDetailsResponse> saveBasicValuation(
            String applicationId,
            BasicValuationDetailsRequest request) {


        return ResponseEntity.ok(
                service.saveBasicValuation(applicationId, request)
        );
    }

    @Override
    public ResponseEntity<BasicValuationDetailsResponse> getBasicValuation(
            String applicationId) {


        return ResponseEntity.ok(
                service.getBasicValuation(applicationId)
        );
    }
}