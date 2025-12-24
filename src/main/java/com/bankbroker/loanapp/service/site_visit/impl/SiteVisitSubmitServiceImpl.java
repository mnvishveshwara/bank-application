package com.bankbroker.loanapp.service.site_visit.impl;

import com.bankbroker.loanapp.dto.site_visit.SiteVisitSubmitResponse;
import com.bankbroker.loanapp.dto.stage.ApplicationHistoryRequest;
import com.bankbroker.loanapp.entity.core.AdminUser;
import com.bankbroker.loanapp.entity.core.LoanApplication;
import com.bankbroker.loanapp.entity.enums.ApplicationHistoryStatus;
import com.bankbroker.loanapp.exception.ResourceNotFoundException;
import com.bankbroker.loanapp.repository.core.ApplicationStageHistoryRepository;
import com.bankbroker.loanapp.repository.core.LoanApplicationRepository;
import com.bankbroker.loanapp.service.core.api.ApplicationStageService;
import com.bankbroker.loanapp.service.site_visit.api.SiteVisitSubmitService;
import com.bankbroker.loanapp.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class SiteVisitSubmitServiceImpl
        implements SiteVisitSubmitService {

    private final LoanApplicationRepository loanApplicationRepository;
    private final ApplicationStageHistoryRepository applicationStageHistoryRepository;
    private final ApplicationStageService applicationStageService;
    private final SecurityUtil securityUtil;

    @Override
    public SiteVisitSubmitResponse submit(String applicationId) {

        AdminUser loggedUser = securityUtil.getLoggedInAdmin();

        LoanApplication application = loanApplicationRepository
                .findById(applicationId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "LoanApplication", "id", applicationId
                        )
                );


        // 🔒 Validate current status (single source of truth)
        ApplicationHistoryStatus currentStatus =
                applicationStageHistoryRepository
                        .findByApplication(application)
                        .map(h -> h.getStatus())
                        .orElseThrow(() ->
                                new IllegalStateException(
                                        "Site visit not completed yet"
                                )
                        );

        if (currentStatus != ApplicationHistoryStatus.SITE_VISIT_COMPLETED) {
            throw new IllegalStateException(
                    "Site visit must be completed before submission"
            );
        }

        // ✅ UPSERT: update existing history row (no new row created)
        applicationStageService.addHistory(
                applicationId,
                ApplicationHistoryRequest.builder()
                        .status(ApplicationHistoryStatus.SITE_VISIT_FORM_SUBMITTED.name())
                        .remarks("Site visit form submitted by valuator")
                        .updatedByAdminId(loggedUser.getId())
                        .build()
        );

        return SiteVisitSubmitResponse.builder()
                .applicationId(applicationId)
                .status(ApplicationHistoryStatus.SITE_VISIT_FORM_SUBMITTED.name())
                .build();
    }
}
