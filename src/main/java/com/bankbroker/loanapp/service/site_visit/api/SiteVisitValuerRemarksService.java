package com.bankbroker.loanapp.service.site_visit.api;


import com.bankbroker.loanapp.dto.site_visit.SiteVisitValuerRemarksRequest;
import com.bankbroker.loanapp.dto.site_visit.SiteVisitValuerRemarksResponse;

public interface SiteVisitValuerRemarksService {

    SiteVisitValuerRemarksResponse saveOrUpdate(
            String applicationId,
            SiteVisitValuerRemarksRequest request
    );

    SiteVisitValuerRemarksResponse getByApplicationId(String applicationId);
}