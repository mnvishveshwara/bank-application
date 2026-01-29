package com.bankbroker.loanapp.controller.core.impl;


import com.bankbroker.loanapp.dto.admin.DashboardLatestApplicationResponse;
import com.bankbroker.loanapp.dto.admin.DashboardMonthlyTrendResponse;
import com.bankbroker.loanapp.dto.admin.DashboardStatusSummaryResponse;
import com.bankbroker.loanapp.service.core.api.AdminDashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/dashboard")
@RequiredArgsConstructor
public class AdminDashboardController {

    private final AdminDashboardService dashboardService;

    @GetMapping("/status-summary")
    public ResponseEntity<List<DashboardStatusSummaryResponse>> statusSummary() {
        return ResponseEntity.ok(dashboardService.getStatusSummary());
    }

    @GetMapping("/monthly-trends")
    public ResponseEntity<List<DashboardMonthlyTrendResponse>> monthlyTrends() {
        return ResponseEntity.ok(dashboardService.getMonthlyTrends());
    }

    @GetMapping("/latest-completed")
    public ResponseEntity<List<DashboardLatestApplicationResponse>> latestCompleted() {
        return ResponseEntity.ok(dashboardService.getLatestCompleted());
    }

    @GetMapping("/latest-rejected")
    public ResponseEntity<List<DashboardLatestApplicationResponse>> latestRejected() {
        return ResponseEntity.ok(dashboardService.getLatestRejected());
    }
}
