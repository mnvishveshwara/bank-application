package com.bankbroker.loanapp.dto.master;

import com.bankbroker.loanapp.entity.enums.ApplicationHistoryStatus;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class AgencyStatus {
    private ApplicationHistoryStatus status;
    private String remarks;
}
