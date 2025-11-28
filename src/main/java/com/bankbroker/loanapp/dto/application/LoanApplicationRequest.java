package com.bankbroker.loanapp.dto.application;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoanApplicationRequest {
    private String clientId;
    private String createdByAdminId;
    private String assignedToAdminId;
}
