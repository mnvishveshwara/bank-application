package com.bankbroker.loanapp.service.site_visit.api;

import com.bankbroker.loanapp.dto.site_visit.SiteVisitPropertyValueAssessmentAmenitiesRequest;
import com.bankbroker.loanapp.dto.site_visit.SiteVisitPropertyValueAssessmentAmenitiesResponse;

public interface SiteVisitPropertyValueAssessmentAmenitiesService {

    SiteVisitPropertyValueAssessmentAmenitiesResponse save(
            String applicationId,
            SiteVisitPropertyValueAssessmentAmenitiesRequest request
    );

    SiteVisitPropertyValueAssessmentAmenitiesResponse get(
            String applicationId
    );
}
