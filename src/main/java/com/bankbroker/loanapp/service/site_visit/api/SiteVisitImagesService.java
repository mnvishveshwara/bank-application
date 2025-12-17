package com.bankbroker.loanapp.service.site_visit.api;

import com.bankbroker.loanapp.dto.site_visit.SiteVisitImageResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface SiteVisitImagesService {

    void uploadAll(
            String applicationId,
            List<MultipartFile> propertySpecificImages,
            List<MultipartFile> unitSpecificImages,
            List<MultipartFile> comparisonImages
    );

    List<SiteVisitImageResponse> getImages(String applicationId);
}