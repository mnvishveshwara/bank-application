package com.bankbroker.loanapp.service.site_visit.api;

import com.bankbroker.loanapp.dto.site_visit.SiteVisitInfrastructureDetailsRequest;
import com.bankbroker.loanapp.dto.site_visit.SiteVisitInfrastructureDetailsResponse;

public interface SiteVisitInfrastructureDetailsService {

    SiteVisitInfrastructureDetailsResponse saveInfrastructureDetails(
            String applicationId,
            SiteVisitInfrastructureDetailsRequest request
    );

    SiteVisitInfrastructureDetailsResponse getInfrastructureDetails(String applicationId);
}
