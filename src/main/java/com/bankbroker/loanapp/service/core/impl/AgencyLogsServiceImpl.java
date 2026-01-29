package com.bankbroker.loanapp.service.core.impl;


import com.bankbroker.loanapp.dto.master.AgencyApplicationLogResponse;
import com.bankbroker.loanapp.entity.core.AdminUser;
import com.bankbroker.loanapp.repository.core.AgencyLogsRepository;
import com.bankbroker.loanapp.service.core.api.AgencyLogsService;
import com.bankbroker.loanapp.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AgencyLogsServiceImpl implements AgencyLogsService {
    private final AgencyLogsRepository agencyLogsRepository;
    private final SecurityUtil securityUtil;

    @Override
    public List<AgencyApplicationLogResponse> getLogsForAgency() {

        AdminUser loggedIn = securityUtil.getLoggedInAdmin();

        if (loggedIn.getAgencyId() == null) {
            throw new IllegalArgumentException("Agency ID not found for logged-in user");
        }

        return agencyLogsRepository.getAgencyApplicationLogs(loggedIn.getAgencyId());
    }
}
