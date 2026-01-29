package com.bankbroker.loanapp.dto.admin;

import com.bankbroker.loanapp.entity.enums.ApplicationHistoryStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DashboardStatusSummaryResponse {

    private ApplicationHistoryStatus status;
    private Long count;
}