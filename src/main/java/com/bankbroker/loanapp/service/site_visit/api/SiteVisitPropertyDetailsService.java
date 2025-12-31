package com.bankbroker.loanapp.service.site_visit.api;

import com.bankbroker.loanapp.dto.site_visit.SiteVisitPropertyBoundaryDetailsRequest;
import com.bankbroker.loanapp.dto.site_visit.SiteVisitPropertyBoundaryDetailsResponse;
import com.bankbroker.loanapp.dto.site_visit.SiteVisitPropertyDetailsRequest;
import com.bankbroker.loanapp.dto.site_visit.SiteVisitPropertyDetailsResponse;

public interface SiteVisitPropertyDetailsService {

    SiteVisitPropertyDetailsResponse savePropertyDetails(
            String applicationId,
            SiteVisitPropertyDetailsRequest request
    );

    SiteVisitPropertyDetailsResponse getPropertyDetails(
            String applicationId
    );

    SiteVisitPropertyBoundaryDetailsResponse savePropertyBoundaryDetails(
            String applicationId,
            SiteVisitPropertyBoundaryDetailsRequest request
    );

    SiteVisitPropertyBoundaryDetailsResponse getPropertyBoundaryDetails(String applicationId);
}
