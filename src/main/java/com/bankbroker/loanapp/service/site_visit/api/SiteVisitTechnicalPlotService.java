package com.bankbroker.loanapp.service.site_visit.api;

import com.bankbroker.loanapp.dto.site_visit.SiteVisitTechnicalPlotRequest;
import com.bankbroker.loanapp.dto.site_visit.SiteVisitTechnicalPlotResponse;

public interface SiteVisitTechnicalPlotService {

    SiteVisitTechnicalPlotResponse save(
            String applicationId,
            SiteVisitTechnicalPlotRequest request
    );

    SiteVisitTechnicalPlotResponse get(String applicationId);
}
