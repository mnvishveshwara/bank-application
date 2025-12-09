package com.bankbroker.loanapp.service;

import com.bankbroker.loanapp.dto.valuator.AssignValuatorRequest;
import com.bankbroker.loanapp.dto.valuator.AssignValuatorResponse;

public interface AssignValuatorService {

    AssignValuatorResponse assignValuator(String applicationId, AssignValuatorRequest request);

    AssignValuatorResponse getValuatorAssignment(String applicationId);
}
