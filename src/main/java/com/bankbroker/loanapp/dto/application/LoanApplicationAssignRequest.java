package com.bankbroker.loanapp.dto.application;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoanApplicationAssignRequest {
    private String assignedToAdminId;
}
