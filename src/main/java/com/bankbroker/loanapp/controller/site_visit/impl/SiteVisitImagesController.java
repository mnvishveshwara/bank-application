package com.bankbroker.loanapp.controller.site_visit.impl;


import com.bankbroker.loanapp.controller.site_visit.api.SiteVisitImagesControllerApi;
import com.bankbroker.loanapp.service.site_visit.SiteVisitImagesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class SiteVisitImagesController implements SiteVisitImagesControllerApi {

    private final SiteVisitImagesService service;

    @Override
    public ResponseEntity<Void> uploadAll(
            String applicationId,
            List<MultipartFile> propertySpecificImages,
            List<MultipartFile> unitSpecificImages,
            List<MultipartFile> comparisonImages) {

        service.uploadAll(
                applicationId,
                propertySpecificImages,
                unitSpecificImages,
                comparisonImages
        );

        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<?> getAll(String applicationId) {
        return ResponseEntity.ok(service.getImages(applicationId));
    }
}