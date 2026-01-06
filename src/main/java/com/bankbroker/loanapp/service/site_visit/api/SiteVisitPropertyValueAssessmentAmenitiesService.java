package com.bankbroker.loanapp.service.site_visit.api;

import com.bankbroker.loanapp.dto.site_visit.SiteVisitPropertyValueAssessmentAmenitiesRequest;
import com.bankbroker.loanapp.dto.site_visit.SiteVisitPropertyValueAssessmentAmenitiesResponse;

public interface SiteVisitPropertyValueAssessmentAmenitiesService {

    SiteVisitPropertyValueAssessmentAmenitiesResponse saveSiteVisitAmenities(
            String applicationId,
            SiteVisitPropertyValueAssessmentAmenitiesRequest request
    );

    SiteVisitPropertyValueAssessmentAmenitiesResponse getSiteVisitAmenities(
            String applicationId
    );
}
