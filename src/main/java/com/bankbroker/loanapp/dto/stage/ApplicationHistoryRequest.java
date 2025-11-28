package com.bankbroker.loanapp.dto.stage;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApplicationHistoryRequest {
    private String status; // enum name
    private String remarks;
    private String updatedByAdminId;
}
