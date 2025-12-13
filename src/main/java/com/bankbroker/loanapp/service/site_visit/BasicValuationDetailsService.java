package com.bankbroker.loanapp.service.site_visit;

import com.bankbroker.loanapp.dto.site_visit.BasicValuationDetailsRequest;
import com.bankbroker.loanapp.dto.site_visit.BasicValuationDetailsResponse;
import com.bankbroker.loanapp.dto.site_visit.PropertyOwnerDetailsResponse;

public interface BasicValuationDetailsService {

    BasicValuationDetailsResponse saveBasicValuation(
            String applicationId,
            BasicValuationDetailsRequest request
    );

    BasicValuationDetailsResponse getBasicValuation(String applicationId);

    PropertyOwnerDetailsResponse getPropertyOwnerDetails(String applicationId);

}
