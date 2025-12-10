package com.bankbroker.loanapp.service;

import com.bankbroker.loanapp.dto.siteVisit.SiteVisitRequest;
import com.bankbroker.loanapp.dto.stage.ApplicationHistoryResponse;
import com.bankbroker.loanapp.dto.valuator.AssignValuatorRequest;
import com.bankbroker.loanapp.dto.valuator.AssignValuatorResponse;
import org.springframework.transaction.annotation.Transactional;

public interface AssignValuatorService {

    AssignValuatorResponse assignValuator(String applicationId, AssignValuatorRequest request);

    AssignValuatorResponse getValuatorAssignment(String applicationId);

    @Transactional
    ApplicationHistoryResponse scheduleSiteVisit(String applicationId, SiteVisitRequest req);
}
