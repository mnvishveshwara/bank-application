package com.bankbroker.loanapp.service.site_visit.api;

import com.bankbroker.loanapp.dto.site_visit.SiteVisitTechnicalSbuaRequest;
import com.bankbroker.loanapp.dto.site_visit.SiteVisitTechnicalSbuaResponse;

public interface SiteVisitTechnicalSbuaService {

    SiteVisitTechnicalSbuaResponse save(
            String applicationId,
            SiteVisitTechnicalSbuaRequest request
    );

    SiteVisitTechnicalSbuaResponse get(String applicationId);
}
