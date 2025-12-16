package com.bankbroker.loanapp.controller.site_visit.impl;


import com.bankbroker.loanapp.controller.site_visit.api.PropertyOwnerDetailsControllerApi;
import com.bankbroker.loanapp.dto.site_visit.PropertyOwnerDetailsResponse;
import com.bankbroker.loanapp.service.site_visit.api.BasicValuationDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class PropertyOwnerDetailsControllerImpl
        implements PropertyOwnerDetailsControllerApi {

    private final BasicValuationDetailsService service;

    @Override
    public ResponseEntity<PropertyOwnerDetailsResponse> getPropertyOwnerDetails(
            String applicationId) {

        log.info("Fetching property owner details for application {}", applicationId);

        return ResponseEntity.ok(
                service.getPropertyOwnerDetails(applicationId)
        );
    }
}
