package com.bankbroker.loanapp.service.core.api;

import com.bankbroker.loanapp.dto.admin.DashboardLatestApplicationResponse;
import com.bankbroker.loanapp.dto.admin.DashboardMonthlyTrendResponse;
import com.bankbroker.loanapp.dto.admin.DashboardStatusSummaryResponse;

import java.util.List;

public interface AdminDashboardService {


    List<DashboardStatusSummaryResponse> getStatusSummary();

    List<DashboardMonthlyTrendResponse> getMonthlyTrends();

    List<DashboardLatestApplicationResponse> getLatestCompleted();

    List<DashboardLatestApplicationResponse> getLatestRejected();
}