package com.bankbroker.loanapp.controller.site_visit.impl;
import com.bankbroker.loanapp.controller.site_visit.api.SiteVisitTechnicalLandControllerApi;
import com.bankbroker.loanapp.dto.site_visit.SiteVisitTechnicalLandDetailsResponse;
import com.bankbroker.loanapp.dto.site_visit.SiteVisitTechnicalLandRequest;
import com.bankbroker.loanapp.service.site_visit.api.SiteVisitTechnicalLandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SiteVisitTechnicalLandControllerImpl
        implements SiteVisitTechnicalLandControllerApi {

    private final SiteVisitTechnicalLandService service;

    @Override
    public ResponseEntity<SiteVisitTechnicalLandDetailsResponse> save(
            String applicationId,
            SiteVisitTechnicalLandRequest request) {

        return ResponseEntity.ok(service.save(applicationId, request));
    }

    @Override
    public ResponseEntity<SiteVisitTechnicalLandDetailsResponse> get(
            String applicationId) {

        return ResponseEntity.ok(service.get(applicationId));
    }

    @Override
    public ResponseEntity<Void> uploadImages(
            String applicationId,
            List<MultipartFile> files) {

        service.uploadImages(applicationId, files);
        return ResponseEntity.ok().build();
    }
}
