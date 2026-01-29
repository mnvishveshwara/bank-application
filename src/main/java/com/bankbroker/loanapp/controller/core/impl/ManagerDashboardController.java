package com.bankbroker.loanapp.controller.core.impl;

import com.bankbroker.loanapp.service.core.impl.ManagerDashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api/manager/dashboard")
@RequiredArgsConstructor
public class ManagerDashboardController {

    private final ManagerDashboardService service;

    @GetMapping("/status-summary")
    public ResponseEntity<?> getStatusSummary() {
        return ResponseEntity.ok(service.getStatusSummary());
    }

    @GetMapping("/monthly-trends")
    public ResponseEntity<?> getMonthlyTrends() {
        return ResponseEntity.ok(service.getMonthlyTrends());
    }

    @GetMapping("/latest-completed")
    public ResponseEntity<?> getLatestCompleted() {
        return ResponseEntity.ok(service.getLatestCompleted());
    }

    @GetMapping("/latest-rejected")
    public ResponseEntity<?> getLatestRejected() {
        return ResponseEntity.ok(service.getLatestRejected());
    }
}
