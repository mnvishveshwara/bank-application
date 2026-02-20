package com.bankbroker.loanapp.dto.master;

import com.bankbroker.loanapp.entity.enums.ApplicationHistoryStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class AgencyApplicationLogResponse {

    private String applicationId;
    private String bankName;

    private ApplicationHistoryStatus latestStage;

    private String valuatorName;
    private LocalDate plannedVisitDate;
    private LocalDateTime lastUpdated;
}
