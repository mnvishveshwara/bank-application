package com.bankbroker.loanapp.service.site_visit;

import com.bankbroker.loanapp.dto.site_visit.SiteVisitTechnicalDetailsRequest;
import com.bankbroker.loanapp.dto.site_visit.SiteVisitTechnicalDetailsResponse;

public interface SiteVisitTechnicalDetailsService {

    SiteVisitTechnicalDetailsResponse saveTechnicalDetails(
            String applicationId,
            SiteVisitTechnicalDetailsRequest request
    );

    SiteVisitTechnicalDetailsResponse getTechnicalDetails(String applicationId);
}
