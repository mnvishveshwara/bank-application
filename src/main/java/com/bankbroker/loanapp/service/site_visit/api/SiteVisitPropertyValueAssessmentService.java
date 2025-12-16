package com.bankbroker.loanapp.service.site_visit.api;


import com.bankbroker.loanapp.dto.site_visit.SiteVisitPropertyValueAssessmentRequest;
import com.bankbroker.loanapp.dto.site_visit.SiteVisitPropertyValueAssessmentResponse;

public interface SiteVisitPropertyValueAssessmentService {

    SiteVisitPropertyValueAssessmentResponse saveOrUpdate(
            String applicationId,
            SiteVisitPropertyValueAssessmentRequest request
    );

    SiteVisitPropertyValueAssessmentResponse getByApplicationId(
            String applicationId
    );
}