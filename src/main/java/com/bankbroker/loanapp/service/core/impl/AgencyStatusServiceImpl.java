package com.bankbroker.loanapp.service.core.impl;

import com.bankbroker.loanapp.dto.master.AgencyStatus;
import com.bankbroker.loanapp.dto.stage.ApplicationHistoryResponse;
import com.bankbroker.loanapp.entity.core.ApplicationStageHistory;
import com.bankbroker.loanapp.entity.core.LoanApplication;
import com.bankbroker.loanapp.exception.ResourceNotFoundException;
import com.bankbroker.loanapp.repository.core.ApplicationStageHistoryRepository;
import com.bankbroker.loanapp.repository.core.LoanApplicationRepository;
import com.bankbroker.loanapp.service.core.api.AgencyStatusService;
import com.bankbroker.loanapp.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AgencyStatusServiceImpl implements AgencyStatusService {

    private final ApplicationStageHistoryRepository historyRepository;
    private final LoanApplicationRepository loanApplicationRepository;
    private final SecurityUtil securityUtil;

    @Override
    public ApplicationHistoryResponse updateApplicationStatus(
            String applicationId,
            AgencyStatus request
    ) {

        LoanApplication app = getApplicationOrThrow(applicationId);

        ApplicationStageHistory history = historyRepository
                .findByApplication(app)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "ApplicationStageHistory",
                                "applicationId",
                                applicationId
                        )
                );

        // Update status
        history.setStatus(request.getStatus());
        history.setUpdatedBy(securityUtil.getLoggedInAdmin());
        history.setRemarks("Applications status updated to " + request.getStatus().name());

        // Save
        historyRepository.save(history);

        // Map to response
        return ApplicationHistoryResponse.builder()
                .applicationId(applicationId)
                .id(history.getId())
                .remarks(history.getRemarks())
                .createdDate(history.getCreatedDate())
                .status(history.getStatus().name())
                .updatedDate(history.getUpdatedDate())
                .build();
    }


    private LoanApplication getApplicationOrThrow(String applicationId) {
        return loanApplicationRepository.findById(applicationId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "LoanApplication", "id", applicationId));
    }
}
