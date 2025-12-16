package com.bankbroker.loanapp.service.site_visit.api;

import com.bankbroker.loanapp.dto.site_visit.SiteVisitBuildingDetailsRequest;
import com.bankbroker.loanapp.dto.site_visit.SiteVisitBuildingDetailsResponse;

public interface SiteVisitBuildingDetailsService {

    SiteVisitBuildingDetailsResponse saveBuildingDetails(
            String applicationId,
            SiteVisitBuildingDetailsRequest request
    );

    SiteVisitBuildingDetailsResponse getBuildingDetails(String applicationId);
}
