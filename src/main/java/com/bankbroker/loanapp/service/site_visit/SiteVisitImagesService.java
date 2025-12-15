package com.bankbroker.loanapp.service.site_visit;

import com.bankbroker.loanapp.dto.site_visit.SiteVisitImageResponse;
import com.bankbroker.loanapp.entity.enums.SiteVisitImageCategory;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface SiteVisitImagesService {

//    void uploadImages(
//            String applicationId,
//            SiteVisitImageCategory category,
//            List<MultipartFile> files
//    );

    void uploadAll(
            String applicationId,
            List<MultipartFile> propertySpecificImages,
            List<MultipartFile> unitSpecificImages,
            List<MultipartFile> comparisonImages
    );

    List<SiteVisitImageResponse> getImages(String applicationId);
}