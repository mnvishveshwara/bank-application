package com.bankbroker.loanapp.controller.site_visit.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequestMapping("/api/applications/{applicationId}/site-visit/images")
public interface SiteVisitImagesControllerApi {

    @PostMapping(consumes = "multipart/form-data")
    ResponseEntity<Void> uploadAll(
            @PathVariable String applicationId,

            @RequestPart(required = false)
            List<MultipartFile> propertySpecificImages,

            @RequestPart(required = false)
            List<MultipartFile> unitSpecificImages,

            @RequestPart(required = false)
            List<MultipartFile> comparisonImages
    );

    @GetMapping
    ResponseEntity<?> getAll(@PathVariable String applicationId);
}