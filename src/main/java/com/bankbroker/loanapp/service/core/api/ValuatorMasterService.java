package com.bankbroker.loanapp.service.core.api;

import com.bankbroker.loanapp.dto.application.LoanApplicationResponse;
import com.bankbroker.loanapp.dto.valuator.ValuatorRequest;
import com.bankbroker.loanapp.dto.valuator.ValuatorResponse;

import java.util.List;

public interface ValuatorMasterService {

    ValuatorResponse createValuator( ValuatorRequest request);

    ValuatorResponse updateValuator(Long valuatorId, ValuatorRequest request);

    ValuatorResponse getValuator(Long valuatorId);

    List<ValuatorResponse> getAllValuators();

    void deleteValuator(Long valuatorId);

    List<LoanApplicationResponse> getApplicationsForLoggedInValuator();
}
