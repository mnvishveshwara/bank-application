package com.bankbroker.loanapp.service.core.impl;

import com.bankbroker.loanapp.dto.admin.DashboardLatestApplicationResponse;
import com.bankbroker.loanapp.dto.admin.DashboardMonthlyTrendResponse;
import com.bankbroker.loanapp.dto.admin.DashboardStatusSummaryResponse;
import com.bankbroker.loanapp.entity.core.AdminUser;
import com.bankbroker.loanapp.entity.core.BankMaster;
import com.bankbroker.loanapp.repository.core.ApplicationStageHistoryRepository;
import com.bankbroker.loanapp.repository.core.LoanApplicationRepository;
import com.bankbroker.loanapp.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class ManagerDashboardService {

    private final ApplicationStageHistoryRepository historyRepository;
    private final SecurityUtil securityUtil;

    private List<Long> getManagerBankIds() {

        AdminUser manager = securityUtil.getLoggedInAdmin();

        return manager.getBanks()
                .stream()
                .map(BankMaster::getId)
                .toList();
    }

    public List<DashboardStatusSummaryResponse> getStatusSummary() {
        return historyRepository.getManagerLatestStatusSummary(getManagerBankIds());
    }

    public List<DashboardMonthlyTrendResponse> getMonthlyTrends() {
        return historyRepository.getManagerMonthlyTrends(getManagerBankIds());
    }

    public List<DashboardLatestApplicationResponse> getLatestCompleted() {
        return historyRepository.getManagerLatestCompleted(getManagerBankIds());
    }

    public List<DashboardLatestApplicationResponse> getLatestRejected() {
        return historyRepository.getManagerLatestRejected(getManagerBankIds());
    }
}
