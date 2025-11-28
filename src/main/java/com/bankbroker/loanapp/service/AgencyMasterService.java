package com.bankbroker.loanapp.service;


import com.bankbroker.loanapp.dto.master.AgencyMasterRequest;
import com.bankbroker.loanapp.dto.master.AgencyMasterResponse;

import java.util.List;

public interface AgencyMasterService {

    AgencyMasterResponse createAgency(AgencyMasterRequest request);

    AgencyMasterResponse updateAgency(Long id, AgencyMasterRequest request);

    AgencyMasterResponse getAgency(Long id);

    List<AgencyMasterResponse> getAllAgencies();

    void deleteAgency(Long id);
}