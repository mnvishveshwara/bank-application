package com.bankbroker.loanapp.service.core.api;

import com.bankbroker.loanapp.dto.site_visit.SiteVisitRequest;
import com.bankbroker.loanapp.dto.stage.ApplicationHistoryResponse;
import com.bankbroker.loanapp.dto.valuator.AssignValuatorRequest;
import com.bankbroker.loanapp.dto.valuator.AssignValuatorResponse;
import org.springframework.transaction.annotation.Transactional;

public interface AssignValuatorService {

    AssignValuatorResponse assignValuator(String applicationId, AssignValuatorRequest request);

    AssignValuatorResponse reAssignValuator(String applicationId, AssignValuatorRequest request);


    AssignValuatorResponse getValuatorAssignment(String applicationId);

    @Transactional
    ApplicationHistoryResponse scheduleSiteVisit(String applicationId, SiteVisitRequest req);

    @Transactional
    ApplicationHistoryResponse reScheduleSiteVisit(String applicationId, SiteVisitRequest req);
}
