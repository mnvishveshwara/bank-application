package com.bankbroker.loanapp.service.core.impl;


import com.bankbroker.loanapp.dto.master.AgencyApplicationLogResponse;
import com.bankbroker.loanapp.entity.core.AdminUser;
import com.bankbroker.loanapp.entity.enums.ApplicationHistoryStatus;
import com.bankbroker.loanapp.repository.core.AgencyLogsRepository;
import com.bankbroker.loanapp.service.core.api.AgencyLogsService;
import com.bankbroker.loanapp.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AgencyLogsServiceImpl implements AgencyLogsService {
    private final AgencyLogsRepository agencyLogsRepository;
    private final SecurityUtil securityUtil;


    @Override
    public Page<AgencyApplicationLogResponse> getLogsForAgency(
            int page,
            int size,
            String search,
            String stage
    ) {
        AdminUser loggedIn = securityUtil.getLoggedInAdmin();

        ApplicationHistoryStatus stageEnum = null;

        if (stage != null && !stage.isBlank()) {
            stageEnum = ApplicationHistoryStatus.valueOf(stage);
        }

        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by(Sort.Direction.DESC, "application.updatedDate")
        );

        return agencyLogsRepository.getAgencyApplicationLogs(
                loggedIn.getAgencyId(),
                search,
                stageEnum,
                pageable
        );
    }
}
