package com.bankbroker.loanapp.service.site_visit;
import com.bankbroker.loanapp.dto.site_visit.SiteVisitValuerDetailsRequest;
import com.bankbroker.loanapp.dto.site_visit.SiteVisitValuerDetailsResponse;

public interface SiteVisitValuerDetailsService {

    SiteVisitValuerDetailsResponse upload(
            String applicationId,
            SiteVisitValuerDetailsRequest request
    );

    SiteVisitValuerDetailsResponse getByApplicationId(
            String applicationId
    );
}