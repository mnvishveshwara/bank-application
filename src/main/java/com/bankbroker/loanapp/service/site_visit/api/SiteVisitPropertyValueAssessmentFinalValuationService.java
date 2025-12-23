package com.bankbroker.loanapp.service.site_visit.api;

import com.bankbroker.loanapp.dto.site_visit.SiteVisitPropertyValueAssessmentFinalValuationRequest;
import com.bankbroker.loanapp.dto.site_visit.SiteVisitPropertyValueAssessmentFinalValuationResponse;

public interface SiteVisitPropertyValueAssessmentFinalValuationService {

    SiteVisitPropertyValueAssessmentFinalValuationResponse save(
            String applicationId,
            SiteVisitPropertyValueAssessmentFinalValuationRequest request
    );

    SiteVisitPropertyValueAssessmentFinalValuationResponse get(
            String applicationId
    );
}
