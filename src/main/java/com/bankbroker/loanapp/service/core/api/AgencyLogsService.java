package com.bankbroker.loanapp.service.core.api;



import com.bankbroker.loanapp.dto.master.AgencyApplicationLogResponse;

import java.util.List;

public interface AgencyLogsService {

    List<AgencyApplicationLogResponse> getLogsForAgency();
}
