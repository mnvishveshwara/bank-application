package com.bankbroker.loanapp.service.site_visit.api;

import com.bankbroker.loanapp.dto.site_visit.SiteVisitTechnicalBuaRequest;
import com.bankbroker.loanapp.dto.site_visit.SiteVisitTechnicalBuaResponse;

public interface SiteVisitTechnicalBuaService {

    SiteVisitTechnicalBuaResponse save(
            String applicationId,
            SiteVisitTechnicalBuaRequest request
    );

    SiteVisitTechnicalBuaResponse get(String applicationId);
}
