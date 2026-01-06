package com.bankbroker.loanapp.service.site_visit.api;

import com.bankbroker.loanapp.dto.site_visit.SiteVisitTechnicalAdditionalRequest;
import com.bankbroker.loanapp.dto.site_visit.SiteVisitTechnicalAdditionalResponse;

public interface SiteVisitTechnicalAdditionalService {

    SiteVisitTechnicalAdditionalResponse saveTechnicalAdditional(
            String applicationId,
            SiteVisitTechnicalAdditionalRequest request
    );

    SiteVisitTechnicalAdditionalResponse getTechnicalAdditional(String applicationId);
}
