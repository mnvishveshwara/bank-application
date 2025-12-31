package com.bankbroker.loanapp.service.site_visit.api;

import com.bankbroker.loanapp.dto.site_visit.SiteVisitTechnicalLandDetailsResponse;
import com.bankbroker.loanapp.dto.site_visit.SiteVisitTechnicalLandRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface SiteVisitTechnicalLandService {

    SiteVisitTechnicalLandDetailsResponse save(
            String applicationId,
            SiteVisitTechnicalLandRequest request
    );

    SiteVisitTechnicalLandDetailsResponse get(String applicationId);

    ResponseEntity<String> uploadImages(
            String applicationId,
            List<MultipartFile> files
    );
}
