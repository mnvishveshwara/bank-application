package com.bankbroker.loanapp.service.site_visit.api;

import com.bankbroker.loanapp.dto.site_visit.SiteVisitSubmitResponse;

public interface SiteVisitSubmitService {

    SiteVisitSubmitResponse submit(String applicationId);
}