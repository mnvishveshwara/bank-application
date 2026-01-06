package com.bankbroker.loanapp.service.site_visit.api;

import com.bankbroker.loanapp.dto.site_visit.SiteVisitTechnicalBuaRequest;
import com.bankbroker.loanapp.dto.site_visit.SiteVisitTechnicalBuaResponse;

public interface SiteVisitTechnicalBuaService {

    SiteVisitTechnicalBuaResponse saveTechnicalBua(
            String applicationId,
            SiteVisitTechnicalBuaRequest request
    );

    SiteVisitTechnicalBuaResponse getTechnicalBua(String applicationId);
}
