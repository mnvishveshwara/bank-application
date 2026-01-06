package com.bankbroker.loanapp.service.site_visit.api;

import com.bankbroker.loanapp.dto.site_visit.SiteVisitPropertyValueAssessmentLandRequest;
import com.bankbroker.loanapp.dto.site_visit.SiteVisitPropertyValueAssessmentLandResponse;

public interface SiteVisitPropertyValueAssessmentLandService {
    SiteVisitPropertyValueAssessmentLandResponse saveLand(
            String applicationId,
            SiteVisitPropertyValueAssessmentLandRequest request
    );

    SiteVisitPropertyValueAssessmentLandResponse getLand(String applicationId);
}
