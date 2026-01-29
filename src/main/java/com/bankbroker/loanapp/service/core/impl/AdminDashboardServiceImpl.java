package com.bankbroker.loanapp.service.core.impl;


import com.bankbroker.loanapp.dto.admin.DashboardLatestApplicationResponse;
import com.bankbroker.loanapp.dto.admin.DashboardMonthlyTrendResponse;
import com.bankbroker.loanapp.dto.admin.DashboardStatusSummaryResponse;
import com.bankbroker.loanapp.repository.core.ApplicationStageHistoryRepository;
import com.bankbroker.loanapp.service.core.api.AdminDashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminDashboardServiceImpl implements AdminDashboardService {

    private final ApplicationStageHistoryRepository historyRepo;

    @Override
    public List<DashboardStatusSummaryResponse> getStatusSummary() {
        return historyRepo.getLatestStatusSummary();
    }

    @Override
    public List<DashboardMonthlyTrendResponse> getMonthlyTrends() {
        return historyRepo.getMonthlyTrends();
    }

    @Override
    public List<DashboardLatestApplicationResponse> getLatestCompleted() {
        return historyRepo.getLatestCompleted().stream().limit(5).toList();
    }

    @Override
    public List<DashboardLatestApplicationResponse> getLatestRejected() {
        return historyRepo.getLatestRejected().stream().limit(5).toList();
    }
}