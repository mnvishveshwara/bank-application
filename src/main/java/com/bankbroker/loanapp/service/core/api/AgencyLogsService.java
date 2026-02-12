package com.bankbroker.loanapp.service.core.api;



import com.bankbroker.loanapp.dto.master.AgencyApplicationLogResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AgencyLogsService {

    Page<AgencyApplicationLogResponse> getLogsForAgency(int page, int size,String search,
                                                        String stage);
}
